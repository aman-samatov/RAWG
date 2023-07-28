package project.rawg.mainpage.model

import androidx.annotation.DrawableRes
import project.rawg.mainpage.db.model.GameEntity

data class Game(
    val id: Int,
    val slug: String,
    val name: String,
    val released: String,
    val backgroundImage: String,
    val rating: Float,
    val ratings: List<Rating>,
    val ratingsCount: Int,
    val metacritic: Int?,
    val parentPlatforms: List<Platform>,
    val genres: List<Genre>,
    val esrbRatingName: String?,
    @DrawableRes val esrbRatingIcon: Int?,
    val shortScreenshots: List<String>
) {
    fun toGameEntity(genreType: String) = GameEntity(
        id = id,
        slug = slug,
        name = name,
        genreType = genreType,
        released = released,
        backgroundImage = backgroundImage,
        rating = rating,
        ratingsCount = ratingsCount,
        metacritic = metacritic,
        esrbRatingName = esrbRatingName,
        esrbRatingIcon = esrbRatingIcon,
        genres = genres,
        platforms = parentPlatforms,
        ratings = ratings,
        shortScreenshots = shortScreenshots
    )
}
