package project.rawg.mainpage.repository.base

import project.rawg.mainpage.model.Game

interface MainPageRemoteRepository {
    suspend fun initialLoading(genre: String): List<Game>
    suspend fun loadMore(genre: String, page: Int): List<Game>
}
