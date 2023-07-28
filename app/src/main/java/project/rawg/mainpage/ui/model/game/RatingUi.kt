package project.rawg.mainpage.ui.model.game

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RatingUi(
    val id: Int,
    val title: String,
    val count: Int,
    val percent: Float
):Parcelable
