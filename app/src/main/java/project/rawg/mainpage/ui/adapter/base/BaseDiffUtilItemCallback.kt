package project.rawg.mainpage.ui.adapter.base

import androidx.recyclerview.widget.DiffUtil
import project.rawg.mainpage.ui.model.base.BaseGameList

open class BaseDiffUtilItemCallback : DiffUtil.ItemCallback<BaseGameList>() {
    override fun areItemsTheSame(oldItem: BaseGameList, newItem: BaseGameList): Boolean =
        oldItem.itemId == newItem.itemId

    override fun areContentsTheSame(oldItem: BaseGameList, newItem: BaseGameList): Boolean {
        return oldItem.equals(newItem)
    }
}
