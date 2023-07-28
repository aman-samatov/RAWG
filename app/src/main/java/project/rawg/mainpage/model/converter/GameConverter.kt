package project.rawg.mainpage.model.converter

import androidx.annotation.DrawableRes
import project.rawg.R
import project.rawg.core.converter.BaseDataConverter
import project.rawg.core.converter.convertList
import project.rawg.mainpage.api.model.GameResponse
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.model.RatingType
import javax.inject.Inject

private const val DEFAULT_VALUE = 0

class GameConverter @Inject constructor(
    private val ratingConverter: RatingConverter,
    private val platformConverter: ParentPlatformConverter,
    private val genreConverter: GenreConverter,
    private val shortScreenshotConverter: ShortScreenshotConverter
) : BaseDataConverter<GameResponse, Game> {
    override fun convert(response: GameResponse): Game {
        return Game(
            id = response.id,
            slug = response.slug,
            name = response.name,
            released = response.released,
            backgroundImage = response.backgroundImage,
            rating = response.rating,
            ratings = response.ratings.convertList(ratingConverter),
            ratingsCount = response.ratingsCount,
            metacritic = response.metacritic ?: DEFAULT_VALUE,
            parentPlatforms = response.parentPlatforms.convertList(platformConverter),
            genres = response.genres.convertList(genreConverter),
            esrbRatingName = response.esrbRating?.name ?: "",
            esrbRatingIcon = getAgeRatingIcon(response.esrbRating?.name),
            shortScreenshots = response.shortScreenshots.convertList(shortScreenshotConverter)
        )
    }

    @DrawableRes
    private fun getAgeRatingIcon(ratingName: String?): Int? = when (ratingName) {
        RatingType.RATING_PENDING.type -> R.drawable.esrb_rp
        RatingType.EVERYONE.type -> R.drawable.esrb_everyone
        RatingType.EVERYONE_TEN.type -> R.drawable.esrb_everyone_10_
        RatingType.TEEN.type -> R.drawable.esrb_teen
        RatingType.MATURE.type -> R.drawable.esrb_mature_17_
        RatingType.ADULTS_ONLY.type -> R.drawable.esrb_adults_only_18_
        else -> null
    }
}