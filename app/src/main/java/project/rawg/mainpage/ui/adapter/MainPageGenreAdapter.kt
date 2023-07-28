package project.rawg.mainpage.ui.adapter

import android.os.Parcelable
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.ui.adapter.base.BaseDiffUtilItemCallback
import project.rawg.mainpage.ui.model.base.BaseGame
import project.rawg.mainpage.ui.model.base.BaseGameList

class MainPageGenreAdapter(
    onItemBind: (GenreType) -> Unit,
    onReadyToLoadMore: (GenreType, Int) -> Unit,
    onGameClick: (BaseGame) -> Unit,
    onRefreshClick: (GenreType) -> Unit,
    scrollStates: MutableMap<Int, Parcelable?>
) :
    AsyncListDifferDelegationAdapter<BaseGameList>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(
            MainPageDelegates.gamesHorizontalDelegate(
                onItemBind = onItemBind,
                onReadyToLoadMore = onReadyToLoadMore,
                onGameClick = onGameClick,
                onRefreshClick = onRefreshClick,
                scrollStates = scrollStates
            )
        )
    }
}
