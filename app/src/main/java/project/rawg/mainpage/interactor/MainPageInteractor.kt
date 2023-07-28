package project.rawg.mainpage.interactor

import kotlinx.coroutines.flow.Flow
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.ui.model.base.BaseGameList

interface MainPageInteractor {
    fun data(): Flow<List<BaseGameList>>
    suspend fun initCategory(genre: GenreType)
    suspend fun tryToLoadMore(genre: GenreType, index: Int)
    suspend fun refresh(genre: GenreType)
}