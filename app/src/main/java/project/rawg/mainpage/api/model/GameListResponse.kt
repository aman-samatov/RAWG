package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class GameListResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String?,
    @SerializedName("previous")
    val previous: String?,
    @SerializedName("results")
    val results: List<GameResponse>,
    @SerializedName("user_platforms")
    val userPlatforms: Boolean
)
