package project.rawg.mainpage.ui.model.base

import android.os.Parcelable
import project.rawg.mainpage.ui.model.game.GenreUi
import project.rawg.mainpage.ui.model.game.PlatformUi
import project.rawg.mainpage.ui.model.game.RatingUi

abstract class BaseGame : BaseGameList, Parcelable {
    abstract val id: Int
    abstract val slug: String
    abstract val name: String
    abstract val released: String
    abstract val backgroundImage: String
    abstract val rating: Float
    abstract val ratings: List<RatingUi>
    abstract val ratingsCount: Int
    abstract val metacritic: String
    abstract val parentPlatforms: List<PlatformUi>
    abstract val genres: List<GenreUi>
    abstract val esrbRatingName: String?
    abstract val esrbRatingIcon: Int?
    abstract val shortScreenshots: List<String>
}
