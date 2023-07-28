package project.rawg.mainpage.ui.adapter

import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.ui.adapter.base.BaseDiffUtilItemCallback
import project.rawg.mainpage.ui.model.base.BaseGame
import project.rawg.mainpage.ui.model.base.BaseGameList

class MainPageGameAdapter(
    onReadyToLoadMore: (Int) -> Unit,
    onGameClick: (BaseGame) -> Unit,
    onRefreshClick: (GenreType) -> Unit
) :
    AsyncListDifferDelegationAdapter<BaseGameList>(BaseDiffUtilItemCallback()) {

    init {
        delegatesManager.addDelegate(
            MainPageDelegates.gameDelegate(
                onReadyToLoadMore,
                onGameClick
            )
        )
            .addDelegate(MainPageDelegates.progressGameDelegate())
            .addDelegate(MainPageDelegates.errorDelegate(onRefreshClick))
            .addDelegate(MainPageDelegates.loadingProgressDelegate())
    }
}
