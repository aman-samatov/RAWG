package project.rawg.detailspage.ui

import android.app.Activity
import android.content.Context
import android.graphics.Typeface
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import project.rawg.R
import project.rawg.mainpage.ui.model.game.RatingUi

private const val VALUE_TEXT_SIZE = 16f
private const val VALUE_FONT_PATH = "fonts/quicksand_light.ttf"
private const val DATA_VALUE_TEXT_SIZE = 12f

class RatingPieDataProvider {
    fun pieData(ratingsUi: List<RatingUi>, context: Context, activity: Activity): PieData {
        val pieEntries = mutableListOf<PieEntry>()
        val ratingMap = mutableMapOf<String, Int>()
        val ratings = ratingsUi.sortedBy {
            it.id
        }
        ratingMap[ratings[0].title] = ratings[0].count
        ratingMap[ratings[1].title] = ratings[1].count
        ratingMap[ratings[2].title] = ratings[2].count
        ratingMap[ratings[3].title] = ratings[3].count

        val colors = mutableListOf<Int>()
        colors.add(ContextCompat.getColor(context, R.color.red))
        colors.add(ContextCompat.getColor(context, R.color.oily_yellow))
        colors.add(ContextCompat.getColor(context, R.color.midnight_blue))
        colors.add(ContextCompat.getColor(context, R.color.emerald_green))

        for (type in ratingMap.keys) {
            pieEntries.add(PieEntry(ratingMap[type]!!.toFloat(), type))
        }
        val pieDataSet = PieDataSet(pieEntries, null)
        pieDataSet.valueTextSize = VALUE_TEXT_SIZE
        pieDataSet.colors = colors
        val pieData = PieData(pieDataSet)
        pieData.setDrawValues(true)
        pieData.setValueTextColor(ContextCompat.getColor(context, R.color.lotus))
        pieData.setValueTextSize(DATA_VALUE_TEXT_SIZE)
        pieData.setValueTypeface(
            Typeface.createFromAsset(
                activity.assets,
                VALUE_FONT_PATH
            )
        )
        return pieData
    }
}