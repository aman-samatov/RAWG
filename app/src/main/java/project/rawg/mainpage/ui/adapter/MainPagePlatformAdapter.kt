package project.rawg.mainpage.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class MainPagePlatformAdapter :
    AsyncListDifferDelegationAdapter<Int>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Int> =
            object : DiffUtil.ItemCallback<Int>() {
                override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
                    return oldItem == newItem
                }
            }
    }

    init {
        delegatesManager.addDelegate(
            MainPageDelegates.platformDelegate()
        )
    }
}