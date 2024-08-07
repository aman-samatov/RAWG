package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class ParentPlatformInfoResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("slug")
    val slug: String
)
