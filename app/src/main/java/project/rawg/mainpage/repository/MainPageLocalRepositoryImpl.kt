package project.rawg.mainpage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import project.rawg.mainpage.db.dao.GamesDao
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.repository.base.MainPageLocalRepository
import javax.inject.Inject

class MainPageLocalRepositoryImpl @Inject constructor(
    private val dao: GamesDao
) : MainPageLocalRepository {

    override suspend fun getGamesList(genre: String): Flow<List<Game>> {
        val games = dao.getGames(genre)
        return games.map { list -> list.map { it.toGame() } }
    }

    override suspend fun putGamesList(data: List<Game>, genre: String) {
        val gamesEntity = data.map {
            it.toGameEntity(genre)
        }
        dao.addGames(gamesEntity)
    }
}
