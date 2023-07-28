package project.rawg.mainpage.ui

import android.os.Parcelable
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import project.rawg.R
import project.rawg.core.mvvm.BaseFragment
import project.rawg.databinding.FragmentMainPageBinding
import project.rawg.detailspage.ui.DetailsPageFragment
import project.rawg.mainpage.ui.adapter.MainPageGenreAdapter
import project.rawg.mainpage.ui.model.game.GameUi
import project.rawg.utils.extensions.addScreen
import project.rawg.utils.extensions.attachAdapter
import project.rawg.utils.viewbinding.viewBinding

@AndroidEntryPoint
class MainPageFragment : BaseFragment(R.layout.fragment_main_page) {
    private val binding: FragmentMainPageBinding by viewBinding()
    private val viewModel: MainPageViewModel by viewModels()
    private val scrollStates: MutableMap<Int, Parcelable?> = mutableMapOf()

    private val adapter by lazy {
        MainPageGenreAdapter(
            onItemBind = viewModel::initCategory,
            onReadyToLoadMore = viewModel::readyToLoadMore,
            onGameClick = { addScreen(DetailsPageFragment.newInstance(it as GameUi)) },
            onRefreshClick = viewModel::refresh,
            scrollStates = scrollStates
        )
    }

    private val layoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun bind() {
        observe(viewModel.gameListStateFlow) { adapter.items = it }
    }

    override fun initViews(view: View) {
        with(binding) {
            recyclerViewGenres.layoutManager = layoutManager
            recyclerViewGenres.adapter = adapter
            recyclerViewGenres.setHasFixedSize(true)
            recyclerViewGenres.itemAnimator = null
            recyclerViewGenres.attachAdapter(adapter)
        }
    }
}
