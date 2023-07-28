package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class ParentPlatformResponse(
    @SerializedName("platform")
    val parentPlatformInfo: ParentPlatformInfoResponse
)
