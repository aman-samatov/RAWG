package project.rawg.mainpage.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.db.database.GamesMediator
import project.rawg.mainpage.interactor.MainPageInteractor
import project.rawg.mainpage.interactor.MainPageInteractorImpl
import project.rawg.mainpage.repository.FakeLocalRepository
import project.rawg.mainpage.repository.FakeRemoteRepository
import project.rawg.mainpage.repository.base.MainPageLocalRepository
import project.rawg.mainpage.repository.base.MainPageRemoteRepository
import project.rawg.mainpage.ui.model.game.GameListUi
import project.rawg.mainpage.ui.model.game.ProgressGame
import project.rawg.utils.extensions.collectFLow

class MainPageViewModelTest {
    private lateinit var viewModel: MainPageViewModel
    private lateinit var interactor: MainPageInteractor
    private lateinit var mediator: GamesMediator
    private lateinit var remoteRepository: MainPageRemoteRepository
    private lateinit var localRepository: MainPageLocalRepository
    private lateinit var genreType: GenreType
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: TestScope

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        genreType = GenreType.Action
        testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)
        remoteRepository = FakeRemoteRepository()
        localRepository = FakeLocalRepository()
        mediator = GamesMediator(
            remoteRepository, localRepository, genreType, testScope
        )
        interactor = MainPageInteractorImpl(
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator,
            mediator
        )
        viewModel = MainPageViewModel(interactor)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `the first loading returns the progress items`() {
        runTest(testDispatcher) {
            delay(1000)
            val data = (viewModel.gameListStateFlow.value[0] as GameListUi).results[0]
            assertThat(data).isEqualTo(ProgressGame)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `initialization of a category returns a list of that's games`() {
        runTest(testDispatcher) {
            viewModel.initCategory(GenreType.Action)
            delay(1000)
            val dataSize = (viewModel.gameListStateFlow.collectFLow(
                this
            ) { it }[0] as GameListUi).results.size

            assertThat(dataSize).isEqualTo(3)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `trying to load more returns a list of games with double size`() {
        runTest(testDispatcher) {
            viewModel.initCategory(genreType)
            delay(1000)
            viewModel.readyToLoadMore(genreType, 2)

            delay(1000)
            val size = viewModel.gameListStateFlow.collectFLow(this) {
                it.first() as GameListUi
            }.results.size

            assertThat(size).isEqualTo(6)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `category's refreshing returns it's updated list of games`() {
        runTest(testDispatcher) {
            viewModel.initCategory(genreType)
            delay(1000)
            viewModel.refresh(genreType)

            delay(1000)
            val size = viewModel.gameListStateFlow.collectFLow(this) {
                it.first() as GameListUi
            }.results.size

            assertThat(size).isEqualTo(6)
            this.coroutineContext.cancelChildren()
        }
    }
}