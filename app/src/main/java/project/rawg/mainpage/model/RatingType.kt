package project.rawg.mainpage.model

enum class RatingType(val type: String) {
    RATING_PENDING("Rating Pending"),
    EVERYONE("Everyone"),
    EVERYONE_TEN("Everyone 10+"),
    TEEN("Teen"),
    MATURE("Mature"),
    ADULTS_ONLY("Adults Only")
}