package project.rawg.mainpage.db.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import project.rawg.mainpage.db.dao.GamesDao
import project.rawg.mainpage.db.model.GameEntity
import project.rawg.mainpage.db.model.converter.GenreTypeConverter
import project.rawg.mainpage.db.model.converter.PlatformTypeConverter
import project.rawg.mainpage.db.model.converter.RatingTypeConverter
import project.rawg.mainpage.db.model.converter.ShortScreenshotTypeConverter

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(
    GenreTypeConverter::class,
    PlatformTypeConverter::class,
    RatingTypeConverter::class,
    ShortScreenshotTypeConverter::class
)

abstract class GamesDataBase : RoomDatabase() {
    abstract val gamesDao: GamesDao
}
