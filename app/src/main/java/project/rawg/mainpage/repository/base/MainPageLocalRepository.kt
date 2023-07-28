package project.rawg.mainpage.repository.base

import kotlinx.coroutines.flow.Flow
import project.rawg.mainpage.model.Game

interface MainPageLocalRepository {
    suspend fun getGamesList(genre: String): Flow<List<Game>>
    suspend fun putGamesList(data: List<Game>, genre: String)
}
