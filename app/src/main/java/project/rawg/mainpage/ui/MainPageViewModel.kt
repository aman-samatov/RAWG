package project.rawg.mainpage.ui

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import project.rawg.core.mvvm.BaseViewModel
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.interactor.MainPageInteractor
import project.rawg.mainpage.ui.model.base.BaseGameList
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainPageViewModel @Inject constructor(
    private val interactor: MainPageInteractor
) : BaseViewModel() {

    private val _gameListStateFlow = MutableStateFlow(emptyList<BaseGameList>())
    val gameListStateFlow = _gameListStateFlow.asStateFlow()

    init {
        launch {
            try {
                interactor.data().collect { list ->
                    _gameListStateFlow.tryEmit(list)
                }
            } catch (e: CancellationException) {
                Timber.e("/*/ Error ${e.message}")
            } catch (t: Throwable) {
                Timber.e("/*/ Error ${t.message}")
            }
        }
    }

    fun initCategory(genre: GenreType) {
        launch {
            try {
                interactor.initCategory(genre)
            } catch (e: CancellationException) {
                Timber.e("/*/ Error ${e.message}")
            } catch (t: Throwable) {
                Timber.e("/*/ Error ${t.message}")
            }
        }
    }

    fun readyToLoadMore(genre: GenreType, index: Int) {
        launch {
            try {
                interactor.tryToLoadMore(genre, index)
            } catch (e: CancellationException) {
                Timber.e("/*/ Error ${e.message}")
            } catch (t: Throwable) {
                Timber.e("/*/ Error ${t.message}")
            }
        }
    }

    fun refresh(genre: GenreType) {
        launch {
            try {
                interactor.refresh(genre)
            } catch (e: CancellationException) {
                Timber.e("/*/ Error ${e.message}")
            } catch (t: Throwable) {
                Timber.e("/*/ Error ${t.message}")
            }
        }
    }
}
