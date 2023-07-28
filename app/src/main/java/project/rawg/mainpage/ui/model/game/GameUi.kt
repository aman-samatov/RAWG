package project.rawg.mainpage.ui.model.game

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import project.rawg.mainpage.ui.model.base.BaseGame

@Parcelize
data class GameUi(
    override val id: Int,
    override val slug: String,
    override val name: String,
    override val released: String,
    override val backgroundImage: String,
    override val rating: Float,
    override val ratings: List<RatingUi>,
    override val ratingsCount: Int,
    override val metacritic: String,
    override val parentPlatforms: List<PlatformUi>,
    override val genres: List<GenreUi>,
    override val esrbRatingName: String?,
    @DrawableRes override val esrbRatingIcon: Int?,
    override val shortScreenshots: List<String>,
) : Parcelable, BaseGame() {
    @IgnoredOnParcel
    override val itemId: Int = id
}
