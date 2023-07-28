package project.rawg.mainpage.db.model.converter

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import project.rawg.mainpage.model.Platform
import javax.inject.Inject

class PlatformTypeConverter @Inject constructor(
) {
    private val gSon = GsonBuilder().create()

    @TypeConverter
    fun toPlatformsList(data: String): List<Platform> {
        if (data.isEmpty())
            return emptyList()
        val result = mutableListOf<Platform>()
        for (item in data.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            result.add(gSon.fromJson(item, Platform::class.java))
        }
        return result
    }

    @TypeConverter
    fun fromGenresList(platforms: List<Platform>): String {
        val result = StringBuilder()
        for (platform in platforms) {
            result.append(gSon.toJson(platform)).append(";")
        }
        return result.toString()
    }
}
