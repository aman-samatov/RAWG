package project.rawg.mainpage.repository

import project.rawg.mainpage.model.Game
import project.rawg.mainpage.repository.base.MainPageRemoteRepository

class FakeRemoteRepository : MainPageRemoteRepository {

    private val gameList = listOf(
        Game(
            1,
            "csgo",
            "CSGO",
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
            "rdr2",
            "RDR2",
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
            "gta5",
            "GTA5",
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

    override suspend fun initialLoading(genre: String): List<Game> {
        return gameList
    }

    override suspend fun loadMore(genre: String, page: Int): List<Game> {
        return gameList
    }
}