package project.rawg.mainpage.api.model

import com.google.gson.annotations.SerializedName

data class GameResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("released")
    val released: String,
    @SerializedName("tba")
    val tba: Boolean,
    @SerializedName("background_image")
    val backgroundImage: String,
    @SerializedName("rating")
    val rating: Float,
    @SerializedName("rating_top")
    val ratingTop: Int,
    @SerializedName("ratings")
    val ratings: List<RatingResponse>,
    @SerializedName("ratings_count")
    val ratingsCount: Int,
    @SerializedName("reviews_text_count")
    val reviewsTextCount: Int,
    @SerializedName("added")
    val added: Int,
    @SerializedName("added_by_status")
    val addedByStatus: AddedByStatusResponse,
    @SerializedName("metacritic")
    val metacritic: Int?,
    @SerializedName("playtime")
    val playtime: Int,
    @SerializedName("suggestions_count")
    val suggestionsCount: Int,
    @SerializedName("updated")
    val updated: String,
    @SerializedName("user_game")
    val userGame: String?,
    @SerializedName("reviews_count")
    val reviewsCount: Int,
    @SerializedName("saturated_color")
    val saturatedColor: String,
    @SerializedName("dominant_color")
    val dominantColor: String,
    @SerializedName("platforms")
    val platforms: List<PlatformResponse>,
    @SerializedName("parent_platforms")
    val parentPlatforms: List<ParentPlatformResponse>,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("stores")
    val stores: List<StoreResponse>?,
    @SerializedName("clip")
    val clip: String?,
    @SerializedName("tags")
    val tags: List<TagResponse>,
    @SerializedName("esrb_rating")
    val esrbRating: EsrbRatingResponse?,
    @SerializedName("short_screenshots")
    val shortScreenshots: List<ShortScreenshotResponse>
)
