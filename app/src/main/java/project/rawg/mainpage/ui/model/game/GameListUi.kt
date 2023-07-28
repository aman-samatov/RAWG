package project.rawg.mainpage.ui.model.game

import android.os.Parcelable
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.ui.model.base.BaseGame
import project.rawg.mainpage.ui.model.base.BaseGameList

@Parcelize
data class GameListUi(
    val genre: GenreType,
    val results: List<BaseGame>
):Parcelable, BaseGameList{
    @IgnoredOnParcel
    override val itemId: Int = genre.hashCode()
}
