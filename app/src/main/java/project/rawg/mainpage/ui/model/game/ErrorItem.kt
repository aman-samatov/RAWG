package project.rawg.mainpage.ui.model.game

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.ui.model.base.BaseGame

@Parcelize
data class ErrorItem(
    val genre: GenreType = GenreType.Action,
    override val id: Int = 0,
    override val slug: String = "",
    override val name: String = "",
    override val released: String = "",
    override val backgroundImage: String = "",
    override val rating: Float = 0f,
    override val ratings: List<RatingUi> = emptyList(),
    override val ratingsCount: Int = 0,
    override val metacritic: String = "",
    override val parentPlatforms: List<PlatformUi> = emptyList(),
    override val genres: List<GenreUi> = emptyList(),
    override val esrbRatingName: String? = null,
    override val esrbRatingIcon: Int? = null,
    override val shortScreenshots: List<String> = emptyList(),
    @IgnoredOnParcel
    override val itemId: Int = id
) : BaseGame(), Parcelable
