package project.rawg.utils.extensions

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import project.rawg.mainpage.db.model.GameEntity
import project.rawg.mainpage.model.Game

class FlowExtensionsKtTest {

    private lateinit var data: MutableList<Game>
    private lateinit var mappedData: MutableList<GameEntity>
    private lateinit var testDispatcher: CoroutineDispatcher
    private lateinit var range: IntRange
    private lateinit var flow: Flow<List<Game>>

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        testDispatcher = UnconfinedTestDispatcher()
        data = mutableListOf()
        mappedData = mutableListOf()
        range = IntRange(1, 5)
        range.forEach {
            data.add(
                Game(
                    id = it,
                    slug = it.toString(),
                    name = it.toString(),
                    released = it.toString(),
                    backgroundImage = it.toString(),
                    rating = it.toFloat(),
                    ratingsCount = it,
                    metacritic = it,
                    esrbRatingName = it.toString(),
                    esrbRatingIcon = it,
                    ratings = emptyList(),
                    parentPlatforms = emptyList(),
                    genres = emptyList(),
                    shortScreenshots = emptyList()
                )
            )
            mappedData.add(
                GameEntity(
                    id = it,
                    slug = it.toString(),
                    name = it.toString(),
                    released = it.toString(),
                    backgroundImage = it.toString(),
                    rating = it.toFloat(),
                    ratingsCount = it,
                    metacritic = it,
                    esrbRatingName = it.toString(),
                    esrbRatingIcon = it,
                    ratings = emptyList(),
                    platforms = emptyList(),
                    genres = emptyList(),
                    shortScreenshots = emptyList(),
                    genreType = it.toString()
                )
            )
        }
        flow = flow { emit(data) }
    }

    @Test
    fun `collect correct type of data from flow, return true`() {
        runTest {
            val actualData = flow.collectFLow(this) {
                it
            }
            val condition = (data == actualData)
            assertTrue(condition)
        }
    }

    @Test
    fun `collect incorrect type of data from flow, return false`() {
        runTest {
            val actualData = flow.collectFLow(this) { list ->
                list.map {
                    it.toGameEntity(it.id.toString())
                }
            }
            val condition = (data != actualData)
            assertTrue(condition)
        }
    }

    @Test
    fun `collect correct type of data from flow with mapping, return true`() {
        runTest {
            val actualData = flow.collectFLow(this) { list ->
                list.map {
                    it.toGameEntity(it.id.toString())
                }
            }
            val condition = (mappedData == actualData)
            assertTrue(condition)
        }
    }

    @Test
    fun `collect incorrect type of data from flow with mapping, return false`() {
        runTest {
            val actualData = flow.collectFLow(this) {
                it
            }
            val condition = (mappedData != actualData)
            assertTrue(condition)
        }
    }
}