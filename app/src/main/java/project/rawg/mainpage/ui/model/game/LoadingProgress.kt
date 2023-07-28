package project.rawg.mainpage.ui.model.game

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import project.rawg.mainpage.ui.model.base.BaseGame

@Parcelize
object LoadingProgress : BaseGame(), Parcelable {
    @IgnoredOnParcel
    override val id = 0

    @IgnoredOnParcel
    override val slug = ""

    @IgnoredOnParcel
    override val name = ""

    @IgnoredOnParcel
    override val released = ""

    @IgnoredOnParcel
    override val backgroundImage = ""

    @IgnoredOnParcel
    override val rating = 0f

    @IgnoredOnParcel
    override val ratings = emptyList<RatingUi>()

    @IgnoredOnParcel
    override val ratingsCount = 0

    @IgnoredOnParcel
    override val metacritic = ""

    @IgnoredOnParcel
    override val parentPlatforms = emptyList<PlatformUi>()

    @IgnoredOnParcel
    override val genres = emptyList<GenreUi>()

    @IgnoredOnParcel
    override val esrbRatingName = null

    @IgnoredOnParcel
    override val esrbRatingIcon = null

    @IgnoredOnParcel
    override val shortScreenshots = emptyList<String>()

    @IgnoredOnParcel
    override val itemId: Int = id
}
