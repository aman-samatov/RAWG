package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class StoreResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("store")
    val storeInfo: StoreInfoResponse
)
