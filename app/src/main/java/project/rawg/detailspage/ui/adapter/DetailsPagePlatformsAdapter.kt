package project.rawg.detailspage.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import project.rawg.mainpage.ui.model.game.PlatformUi

class DetailsPagePlatformsAdapter :
    AsyncListDifferDelegationAdapter<PlatformUi>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<PlatformUi> =
            object : DiffUtil.ItemCallback<PlatformUi>() {
                override fun areItemsTheSame(oldItem: PlatformUi, newItem: PlatformUi): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: PlatformUi, newItem: PlatformUi): Boolean {
                    return oldItem.name == newItem.name
                }
            }
    }

    init {
        delegatesManager.addDelegate(DetailsPageDelegates.platformDelegate())
    }
}