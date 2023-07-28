package project.rawg.mainpage.db.database

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.flow.MutableStateFlow
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
import project.rawg.mainpage.api.PagingState
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.model.GameCategoryModel
import project.rawg.mainpage.repository.FakeLocalRepository
import project.rawg.mainpage.repository.FakeRemoteRepository
import project.rawg.mainpage.repository.base.MainPageLocalRepository
import project.rawg.mainpage.repository.base.MainPageRemoteRepository
import project.rawg.utils.extensions.collectFLow


class GamesMediatorTest {

    private lateinit var gamesMediator: GamesMediator
    private lateinit var remoteRepository: MainPageRemoteRepository
    private lateinit var localRepository: MainPageLocalRepository
    private lateinit var genreType: GenreType

    private lateinit var testDispatcher: TestDispatcher
    private lateinit var dataStateFlow: MutableStateFlow<GameCategoryModel>
    private lateinit var testScope: TestScope

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        genreType = GenreType.Action
        dataStateFlow = MutableStateFlow(convertToModel(PagingState.Initial))
        testDispatcher = StandardTestDispatcher()
        testScope = TestScope(testDispatcher)
        Dispatchers.setMain(testDispatcher)
        remoteRepository = FakeRemoteRepository()
        localRepository = FakeLocalRepository()
        gamesMediator = GamesMediator(
            remoteRepository, localRepository, genreType, testScope
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun teardown() {
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun `data returns initial paging state`() {
        runTest(testDispatcher) {
            val initialData = gamesMediator.data()
            assertEquals(dataStateFlow.collectFLow(this) { it },
                initialData.collectFLow(this) { it })
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `initialization of data with content in db returns data`() {
        val existingGames = listOf(
            Game(
                1,
                "cs",
                "CS",
                "1",
                "png",
                3f,
                emptyList(),
                0,
                null,
                emptyList(),
                emptyList(),
                null,
                null,
                emptyList()
            ),
            Game(
                2,
                "rdr",
                "RDR",
                "1",
                "png",
                3f,
                emptyList(),
                0,
                null,
                emptyList(),
                emptyList(),
                null,
                null,
                emptyList()
            ),
            Game(
                3,
                "gta",
                "GTA",
                "1",
                "png",
                3f,
                emptyList(),
                0,
                null,
                emptyList(),
                emptyList(),
                null,
                null,
                emptyList()
            )
        )
        runTest(testDispatcher) {
            localRepository.putGamesList(
                existingGames, GenreType.Action.genreTitle
            )
            gamesMediator.init()
            val actualGames = gamesMediator.data().collectFLow(this) { it }

            assertEquals(existingGames, (actualGames.dataState as PagingState.Content).data)

            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `initialization of data without content in db returns data from remote`() {
        runTest(testDispatcher) {
            gamesMediator.init()

            val actualGames = gamesMediator.data().collectFLow(this) { it }
            val existingGames = localRepository.getGamesList(GenreType.Action.genreTitle)
                .collectFLow(this) { it }

            assertEquals(existingGames, (actualGames.dataState as PagingState.Content).data)

            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `trying to load more returns list of games with double size`() {
        runTest(testDispatcher) {

            gamesMediator.init()

            gamesMediator.tryToLoadMore(2)

            val size = (gamesMediator.data().collectFLow(this) { it }
                .dataState as PagingState.Content).data.size

            assertEquals(6, size)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun `refreshing returns updated list of games of that category`() {
        runTest(testDispatcher) {
            gamesMediator.init()
            gamesMediator.refresh()
            val size = (gamesMediator.data().collectFLow(this) { it }
                .dataState as PagingState.Content).data.size
            assertEquals(6, size)
            this.coroutineContext.cancelChildren()
        }
    }

    private fun convertToModel(state: PagingState<List<Game>>) = GameCategoryModel(
        genreType = genreType,
        dataState = state,
    )
}