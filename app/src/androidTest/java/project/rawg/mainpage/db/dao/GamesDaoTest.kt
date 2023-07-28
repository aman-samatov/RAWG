package project.rawg.mainpage.db.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.db.database.GamesDataBase
import project.rawg.mainpage.db.model.GameEntity
import project.rawg.utils.extensions.collectFLow
import javax.inject.Inject
import javax.inject.Named

@SmallTest
@HiltAndroidTest
class GamesDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: GamesDataBase
    private lateinit var dao: GamesDao
    private lateinit var testDispatcher: TestDispatcher

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        testDispatcher = StandardTestDispatcher()
        Dispatchers.setMain(testDispatcher)
        hiltRule.inject()
        dao = database.gamesDao
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @After
    fun teardown() {
        database.close()
        Dispatchers.resetMain()
        testDispatcher.cancel()
    }

    @Test
    fun addGames() {
        runTest(testDispatcher) {
            val games = listOf(
                GameEntity(
                    id = 1,
                    gameId = 1,
                    slug = "game",
                    name = "Game",
                    genreType = GenreType.Action.genreTitle,
                    released = "22",
                    backgroundImage = "1",
                    rating = 5f,
                    ratingsCount = 3,
                    metacritic = null,
                    esrbRatingName = null,
                    esrbRatingIcon = null,
                    genres = emptyList(),
                    platforms = emptyList(),
                    ratings = emptyList(),
                    shortScreenshots = emptyList()
                )
            )
            dao.addGames(games)

            val existingGames =
                dao.getGames(GenreType.Action.genreTitle).collectFLow(this) { it }

            assertEquals(existingGames, games)
            this.coroutineContext.cancelChildren()
        }
    }

    @Test
    fun getGames() {
        runTest(testDispatcher) {
            val existingGames =
                dao.getGames(GenreType.Action.genreTitle).collectFLow(this) { it }

            assertEquals(existingGames, emptyList<GameEntity>())
            this.coroutineContext.cancelChildren()
        }
    }
}