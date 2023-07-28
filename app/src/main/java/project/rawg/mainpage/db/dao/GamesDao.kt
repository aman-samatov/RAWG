package project.rawg.mainpage.db.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import project.rawg.mainpage.db.model.*

@Dao
interface GamesDao {

    @Query("SELECT * FROM games WHERE genre_type = :genre")
    fun getGames(genre: String): Flow<List<GameEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addGames(games: List<GameEntity>)
}
