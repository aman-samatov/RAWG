package project.rawg.mainpage.interactor

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.db.database.GamesMediator
import project.rawg.mainpage.repository.FakeLocalRepository
import project.rawg.mainpage.repository.FakeRemoteRepository
import project.rawg.mainpage.repository.base.MainPageLocalRepository
import project.rawg.mainpage.repository.base.MainPageRemoteRepository
import project.rawg.mainpage.ui.model.game.GameListUi
import project.rawg.mainpage.ui.model.game.GameUi
import project.rawg.mainpage.ui.model.game.ProgressGame
import project.rawg.utils.extensions.collectFLow

class MainPageInteractorImplTest {
    private lateinit var interactor: MainPageInteractor
    private lateinit var mediator: GamesMediator
    private lateinit var remoteRepository: MainPageRemoteRepository
    private lateinit var localRepository: MainPageLocalRepository
    private lateinit var genreType: GenreType
    private lateinit var testDispatcher: TestDispatcher
    private lateinit var testScope: TestScope

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
            val data = interactor.data().collectFLow(this) { it }
            val condition = (data[0] as GameListUi).results.contains(ProgressGame)
            assertTrue(condition)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `initialization of a category returns a list of that's games`() {
        runTest(testDispatcher) {
            interactor.initCategory(genreType)
            val actualData = interactor.data().collectFLow(this) { list ->
                ((list.first() as GameListUi).results.first() as GameUi).slug
            }

            assertEquals("csgo", actualData)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `trying to load more returns a list of games with double size`() {
        runTest(testDispatcher) {
            interactor.initCategory(genreType)
            interactor.tryToLoadMore(genreType, 2)

            val size = interactor.data().collectFLow(this) {
                it.first() as GameListUi
            }.results.size

            assertEquals(6, size)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `category's refreshing returns it's updated list of games`() {
        runTest(testDispatcher) {
            interactor.initCategory(genreType)
            interactor.refresh(genreType)
            val size = interactor.data().collectFLow(this) {
                it.first() as GameListUi
            }.results.size

            assertEquals(6, size)
            this.coroutineContext.cancelChildren()
        }
    }
}