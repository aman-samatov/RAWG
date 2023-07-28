package project.rawg.mainpage.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.model.Genre
import project.rawg.mainpage.model.Platform
import project.rawg.mainpage.model.Rating

@Entity(
    tableName = "games",
    indices = [Index(value = ["id", "genre_type"], unique = true)]
)

data class GameEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "game_id")
    var gameId: Long = 0,
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "slug")
    val slug: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "genre_type")
    val genreType: String,
    @ColumnInfo(name = "released")
    val released: String,
    @ColumnInfo(name = "background_image")
    val backgroundImage: String,
    @ColumnInfo(name = "rating")
    val rating: Float,
    @ColumnInfo(name = "ratings_count")
    val ratingsCount: Int,
    @ColumnInfo(name = "genres")
    val genres: List<Genre>,
    @ColumnInfo(name = "platforms")
    val platforms: List<Platform>,
    @ColumnInfo(name = "ratings")
    val ratings: List<Rating>,
    @ColumnInfo(name = "short_screenshots")
    val shortScreenshots: List<String>,
    @ColumnInfo(name = "metacritic")
    val metacritic: Int?,
    @ColumnInfo(name = "esrb_rating_name")
    val esrbRatingName: String?,
    @ColumnInfo(name = "esrb_rating_icon")
    val esrbRatingIcon: Int?
) {
    fun toGame() = Game(
        id = id,
        slug = slug,
        name = name,
        released = released,
        backgroundImage = backgroundImage,
        rating = rating,
        ratingsCount = ratingsCount,
        metacritic = metacritic,
        esrbRatingName = esrbRatingName,
        esrbRatingIcon = esrbRatingIcon,
        ratings = ratings,
        parentPlatforms = platforms,
        genres = genres,
        shortScreenshots = shortScreenshots
    )
}
