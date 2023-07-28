package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class PlatformResponse(
    @SerializedName("platform")
    val platformInfo: PlatformInfoResponse,
    @SerializedName("released_at")
    val releasedAt: String?,
    @SerializedName("requirements_en")
    val requirementsEn: String?,
    @SerializedName("requirements_ru")
    val requirementsRu: String?
)
