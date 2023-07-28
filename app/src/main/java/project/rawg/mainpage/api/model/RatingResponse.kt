package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class RatingResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("percent")
    val percent: Float
)
