package project.rawg.mainpage.db.model.converter

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import project.rawg.mainpage.model.Rating
import javax.inject.Inject

class RatingTypeConverter @Inject constructor(
) {
    private val gSon = GsonBuilder().create()

    @TypeConverter
    fun toRatingsList(data: String): List<Rating> {
        if (data.isEmpty()) return emptyList()
        val result = mutableListOf<Rating>()
        for (item in data.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            result.add(gSon.fromJson(item, Rating::class.java))
        }
        return result
    }

    @TypeConverter
    fun fromRatingsList(ratings: List<Rating>): String {
        val result = StringBuilder()
        for (rating in ratings) {
            result.append(gSon.toJson(rating)).append(";")
        }
        return result.toString()
    }
}
