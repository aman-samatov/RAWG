package project.rawg.mainpage.model

import androidx.annotation.DrawableRes
import project.rawg.mainpage.ui.model.game.PlatformUi

data class Platform(
    val name: String,
    @DrawableRes val image: Int
) {
    fun toPlatformUi() =
        PlatformUi(
            name = name,
            image = image
        )
}
