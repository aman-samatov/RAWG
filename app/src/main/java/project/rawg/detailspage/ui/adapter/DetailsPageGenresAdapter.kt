package project.rawg.detailspage.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import project.rawg.mainpage.ui.model.game.GenreUi

class DetailsPageGenresAdapter :
    AsyncListDifferDelegationAdapter<GenreUi>(DIFF_CALLBACK) {

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<GenreUi> =
            object : DiffUtil.ItemCallback<GenreUi>() {
                override fun areItemsTheSame(oldItem: GenreUi, newItem: GenreUi): Boolean =
                    oldItem == newItem

                override fun areContentsTheSame(oldItem: GenreUi, newItem: GenreUi): Boolean {
                    return oldItem.name == newItem.name
                }
            }
    }

    init {
        delegatesManager.addDelegate(DetailsPageDelegates.genreDelegate())
    }
}