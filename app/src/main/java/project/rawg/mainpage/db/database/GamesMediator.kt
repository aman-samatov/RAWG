package project.rawg.mainpage.db.database

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.api.PagingState
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.model.GameCategoryModel
import project.rawg.mainpage.repository.base.MainPageLocalRepository
import project.rawg.mainpage.repository.base.MainPageRemoteRepository
import project.rawg.utils.Constants
import project.rawg.utils.extensions.collectFLow
import javax.inject.Inject

class GamesMediator @Inject constructor(
    private val remoteRepository: MainPageRemoteRepository,
    private val localRepository: MainPageLocalRepository,
    private val genreType: GenreType,
    private val scope: CoroutineScope,
) {

    private var genre: String? = ""
    private var page = Constants.DEFAULT_PAGE


    private val dataStateFlow = MutableStateFlow(convertToModel(PagingState.Initial))

    fun data(): Flow<GameCategoryModel> = dataStateFlow

    suspend fun init(refresh: Boolean = false) {
        val state: PagingState<List<Game>> = if (refresh) PagingState.Initial
        else dataStateFlow.value.dataState
        if (state is PagingState.Initial) {
            try {
                val localData =
                    localRepository.getGamesList(genreType.genreTitle).collectFLow(
                        scope
                    ) {
                        it
                    }
                if (localData.isEmpty()) {
                    val data = remoteRepository.initialLoading(genreType.genreTitle)
                    dataStateFlow.emit(convertToModel(PagingState.Content(data)))
                    localRepository.putGamesList(data, genreType.genreTitle)
                } else {
                    dataStateFlow.emit(convertToModel(PagingState.Content(localData)))
                }

            } catch (e: Exception) {
                dataStateFlow.emit(convertToModel(PagingState.Error))
                throw e
            }
        }
    }

    suspend fun tryToLoadMore(index: Int) {
        val state = dataStateFlow.value.dataState
        if (state is PagingState.Content && index == state.data.size - 1) {
            loadMore()
        }
    }

    suspend fun refresh() {
        if (dataStateFlow.value.dataState is PagingState.Error) {
            init(refresh = true)
        } else {
            updateParams(
                genre = genreType.genreTitle,
                alreadyLoadedCount = localRepository.getGamesList(genreType.genreTitle)
                    .collectFLow(
                        scope
                    ) {
                        it
                    }.size
            )
            loadMore()
        }
    }

    private fun updateParams(genre: String, alreadyLoadedCount: Int) {
        this.genre = genre
        if (alreadyLoadedCount < 0) throw IllegalArgumentException()
        page = (alreadyLoadedCount / (Constants.PAGE_SIZE + 1)) + 1
    }

    private suspend fun loadMore() {
        val currentData = (dataStateFlow.value.dataState as? PagingState.Content)?.data
            ?: (dataStateFlow.value.dataState as? PagingState.Persist)?.data
            ?: return
        dataStateFlow.emit(convertToModel(PagingState.Paging(currentData)))
        try {
            updateParams(
                genre = genreType.genreTitle,
                alreadyLoadedCount = localRepository.getGamesList(genreType.genreTitle)
                    .collectFLow(
                        scope
                    ) {
                        it
                    }.size
            )
            val data = remoteRepository.loadMore(genre!!, page)
            dataStateFlow.emit(convertToModel(PagingState.Content(currentData.plus(data))))
            localRepository.putGamesList(data, genreType.genreTitle)
        } catch (e: Exception) {
            val flow = localRepository.getGamesList(genreType.genreTitle)
            val localData = flow.collectFLow(scope) {
                it
            }
            if (localData.isEmpty()) {
                dataStateFlow.emit(convertToModel(PagingState.Error))
            } else {
                dataStateFlow.emit(convertToModel(PagingState.Persist(localData)))
            }
            throw e
        }
    }

    private fun convertToModel(state: PagingState<List<Game>>) = GameCategoryModel(
        genreType = genreType,
        dataState = state,
    )
}
