package project.rawg.mainpage.db.model.converter

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import project.rawg.mainpage.model.Genre
import javax.inject.Inject

class GenreTypeConverter @Inject constructor(
) {
    private val gSon = GsonBuilder().create()

    @TypeConverter
    fun toGenresList(data: String): List<Genre> {
        if (data.isEmpty())
            return emptyList()
        val result = mutableListOf<Genre>()
        for (item in data.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            result.add(gSon.fromJson(item, Genre::class.java))
        }
        return result
    }

    @TypeConverter
    fun fromGenresList(genres: List<Genre>): String {
        val result = StringBuilder()
        for (genre in genres) {
            result.append(gSon.toJson(genre)).append(";")
        }
        return result.toString()
    }
}
