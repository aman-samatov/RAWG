package project.rawg.mainpage.model

import project.rawg.mainpage.ui.model.game.RatingUi

data class Rating(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Float
) {

    fun toRatingUi() =
        RatingUi(
            id = id,
            title = title,
            count = count,
            percent = percent
        )
}
