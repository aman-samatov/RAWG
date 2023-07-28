package project.rawg.mainpage.ui.adapter

import android.os.Parcelable
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import project.rawg.R
import project.rawg.databinding.ItemErrorBinding
import project.rawg.databinding.ItemGameBinding
import project.rawg.databinding.ItemLoadingProgressBinding
import project.rawg.databinding.ItemPlatformBinding
import project.rawg.databinding.ItemProgressGameBinding
import project.rawg.databinding.ItemRowParentBinding
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.ui.model.base.BaseGame
import project.rawg.mainpage.ui.model.base.BaseGameList
import project.rawg.mainpage.ui.model.game.ErrorItem
import project.rawg.mainpage.ui.model.game.GameListUi
import project.rawg.mainpage.ui.model.game.GameUi
import project.rawg.mainpage.ui.model.game.LoadingProgress
import project.rawg.mainpage.ui.model.game.ProgressGame
import project.rawg.utils.extensions.setThrottleOnClickListener


private const val IMAGE_LOADING_DURATION = 600
private const val ROUNDED_CORNERS_RADIUS = 16f
private const val SPAN_COUNT = 2

object MainPageDelegates {


    fun gameDelegate(
        onReadyToLoadMore: (Int) -> Unit,
        onGameClick: (BaseGame) -> Unit
    ) = adapterDelegateViewBinding<GameUi, BaseGameList, ItemGameBinding>(
        { inflater, container -> ItemGameBinding.inflate(inflater, container, false) }
    ) {
        val adapter = MainPagePlatformAdapter()
        bind {
            with(binding) {
                textViewName.text = item.name
                textViewMetaScore.text = item.metacritic
                textViewScore.text = item.rating.toString()
                ratingBar.rating = item.rating
                imageViewIcon.load(item.backgroundImage) {
                    crossfade(true)
                    crossfade(IMAGE_LOADING_DURATION)
                    transformations(RoundedCornersTransformation(ROUNDED_CORNERS_RADIUS))
                }
                onReadyToLoadMore.invoke(bindingAdapterPosition)
                binding.root.setThrottleOnClickListener { onGameClick.invoke(item) }
                recyclerViewPlatforms.setThrottleOnClickListener { onGameClick.invoke(item) }
                recyclerViewPlatforms.layoutManager =
                    GridLayoutManager(binding.root.context, SPAN_COUNT)
                recyclerViewPlatforms.adapter = adapter
                adapter.items = item.parentPlatforms.map {
                    it.image
                }
            }
        }
    }

    fun platformDelegate() =
        adapterDelegateViewBinding<Int, Int, ItemPlatformBinding>(
            { inflater, container -> ItemPlatformBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.imageViewIcon.load(item)
            }
        }

    fun progressGameDelegate() =
        adapterDelegateViewBinding<ProgressGame, BaseGameList, ItemProgressGameBinding>(
            { inflater, container -> ItemProgressGameBinding.inflate(inflater, container, false) }
        ) {
            val animation =
                AnimationUtils.loadAnimation(binding.root.context, R.anim.progress_fade_out)
            binding.root.startAnimation(animation)
        }

    fun loadingProgressDelegate() =
        adapterDelegateViewBinding<LoadingProgress, BaseGameList, ItemLoadingProgressBinding>(
            { inflater, container ->
                ItemLoadingProgressBinding.inflate(
                    inflater,
                    container,
                    false
                )
            }
        ) {}

    fun errorDelegate(onRefreshClick: (GenreType) -> Unit) =
        adapterDelegateViewBinding<ErrorItem, BaseGameList, ItemErrorBinding>(
            { inflater, container -> ItemErrorBinding.inflate(inflater, container, false) }
        ) {
            bind {
                binding.root.setThrottleOnClickListener { onRefreshClick.invoke(item.genre) }
            }
        }

    fun gamesHorizontalDelegate(
        onItemBind: (GenreType) -> Unit,
        onReadyToLoadMore: (GenreType, Int) -> Unit,
        onGameClick: (BaseGame) -> Unit,
        onRefreshClick: (GenreType) -> Unit,
        scrollStates: MutableMap<Int, Parcelable?>
    ) =
        adapterDelegateViewBinding<GameListUi, BaseGameList, ItemRowParentBinding>(
            { inflater, container ->
                ItemRowParentBinding.inflate(inflater, container, false)
            }
        ) {
            val adapter = MainPageGameAdapter(
                onReadyToLoadMore = { pos -> onReadyToLoadMore.invoke(item.genre, pos) },
                onGameClick = onGameClick,
                onRefreshClick = onRefreshClick,
            )
            with(binding) {
                recyclerViewGames.layoutManager =
                    LinearLayoutManager(binding.root.context, LinearLayoutManager.HORIZONTAL, false)
                recyclerViewGames.adapter = adapter
                bind {
                    onItemBind.invoke(item.genre)
                    adapter.apply {
                        textViewGenreTitle.text = item.genre.genreTitle
                        items = item.results
                    }
                    scrollStates[bindingAdapterPosition]?.let {
                        recyclerViewGames.layoutManager?.onRestoreInstanceState(it)
                        scrollStates.remove(bindingAdapterPosition)
                    }
                }
                onViewRecycled {
                    recyclerViewGames.layoutManager?.onSaveInstanceState()?.let {
                        scrollStates[bindingAdapterPosition] = it
                    }
                }
            }
        }
}
