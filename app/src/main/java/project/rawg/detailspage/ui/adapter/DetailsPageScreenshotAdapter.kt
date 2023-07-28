package project.rawg.detailspage.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class DetailsPageScreenshotAdapter(
    onScreenshotClick: (String) -> Unit
) :
    AsyncListDifferDelegationAdapter<String>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<String> =
            object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }
            }
    }

    init {
        delegatesManager.addDelegate(
            DetailsPageDelegates.screenshotDelegate(
                onScreenshotClick = onScreenshotClick
            )
        )
    }
}
