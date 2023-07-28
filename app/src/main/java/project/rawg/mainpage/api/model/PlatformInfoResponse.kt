package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class PlatformInfoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("year_end")
    val yearEnd: Int?,
    @SerializedName("year_start")
    val yearStart: Int?,
    @SerializedName("games_count")
    val gamesCount: Int,
    @SerializedName("image_background")
    val imageBackground: String?
)
