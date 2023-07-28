package project.rawg.mainpage.ui.model.game

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class GenreUi(
    val name: String,
    @DrawableRes val image: Int
) : Parcelable
