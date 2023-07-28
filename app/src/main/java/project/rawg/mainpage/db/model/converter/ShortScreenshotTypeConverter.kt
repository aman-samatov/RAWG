package project.rawg.mainpage.db.model.converter

import androidx.room.TypeConverter
import com.google.gson.GsonBuilder
import javax.inject.Inject

class ShortScreenshotTypeConverter @Inject constructor(
) {
    private val gSon = GsonBuilder().create()

    @TypeConverter
    fun toShortScreenshotsList(data: String): List<String> {
        if (data.isEmpty())
            return emptyList()
        val result = mutableListOf<String>()
        for (item in data.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()) {
            result.add(gSon.fromJson(item, String::class.java))
        }
        return result
    }

    @TypeConverter
    fun fromShortScreenshotsList(shortScreenshots: List<String>): String {
        val result = StringBuilder()
        for (shortScreenshot in shortScreenshots) {
            result.append(gSon.toJson(shortScreenshot)).append(";")
        }
        return result.toString()
    }
}
