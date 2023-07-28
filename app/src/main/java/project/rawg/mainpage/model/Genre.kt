package project.rawg.mainpage.model

import androidx.annotation.DrawableRes
import project.rawg.mainpage.ui.model.game.GenreUi

data class Genre(
    val name: String,
    @DrawableRes val image: Int
) {


    fun toGenreUi() =
        GenreUi(
            name = name,
            image = image
        )
}
