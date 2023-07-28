package project.rawg.detailspage.ui

import android.graphics.Typeface
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import coil.load
import project.rawg.R
import project.rawg.core.mvvm.BaseFragment
import project.rawg.databinding.FragmentDetailsPageBinding
import project.rawg.detailspage.ui.adapter.DetailsPageGenresAdapter
import project.rawg.detailspage.ui.adapter.DetailsPagePlatformsAdapter
import project.rawg.detailspage.ui.adapter.DetailsPageScreenshotAdapter
import project.rawg.mainpage.ui.model.game.GameUi
import project.rawg.screenshotpage.ScreenshotFragment
import project.rawg.utils.Arguments
import project.rawg.utils.extensions.addScreen
import project.rawg.utils.extensions.args
import project.rawg.utils.extensions.attachAdapter
import project.rawg.utils.extensions.popScreen
import project.rawg.utils.extensions.withArgs
import project.rawg.utils.viewbinding.viewBinding

private const val IMAGE_LOADING_DURATION = 300
private const val ANIMATION_DURATION = 1000
private const val TRANSPARENT_CIRCLE_RADIUS = 38f
private const val HOLE_RADIUS = 36f
private const val CENTER_TEXT_SIZE = 20f
private const val ENTRY_LABEL_TEXT_SIZE = 12f
private const val CENTER_PATH = "fonts/quicksand_medium.ttf"
private const val SPAN_COUNT = 4

class DetailsPageFragment : BaseFragment(R.layout.fragment_details_page) {
    companion object {
        fun newInstance(game: GameUi) =
            DetailsPageFragment().withArgs(Arguments.GAME_DATA to game)
    }

    private val binding: FragmentDetailsPageBinding by viewBinding()

    private val game: GameUi by args(Arguments.GAME_DATA)

    private val screenshotAdapter by lazy {
        DetailsPageScreenshotAdapter(onScreenshotClick = {
            addScreen(
                ScreenshotFragment.newInstance(it),
                popExit = R.anim.fade_out,
                popEnter = R.anim.fade_in
            )
        })
    }

    private val genresAdapter by lazy {
        DetailsPageGenresAdapter()
    }

    private val platformsAdapter by lazy {
        DetailsPagePlatformsAdapter()
    }

    private val gridLayoutManager: GridLayoutManager by lazy {
        GridLayoutManager(
            requireContext(),
            SPAN_COUNT
        )
    }

    override fun bind() {
        with(binding) {
            setToolBar(toolbar)
            imageViewIcon.load(game.backgroundImage) {
                crossfade(true)
                crossfade(IMAGE_LOADING_DURATION)
            }
            textViewName.text = game.name
            textViewScore.text = game.rating.toString()
            ratingBar.rating = game.rating
            textViewReleased.append(game.released)
            imageViewAgeRatingIcon.load(game.esrbRatingIcon)

            pieChartRatings.data =
                RatingPieDataProvider().pieData(game.ratings, requireContext(), requireActivity())
            pieChartRatings.animateXY(ANIMATION_DURATION, ANIMATION_DURATION)
            pieChartRatings.invalidate()
            pieChartRatings.setHoleColor(
                ContextCompat.getColor(requireContext(), R.color.layout_background)
            )
            pieChartRatings.centerText = getString(R.string.total) + game.ratingsCount.toString()
            pieChartRatings.setCenterTextColor(
                ContextCompat.getColor(requireContext(), R.color.lotus)
            )
            pieChartRatings.setCenterTextTypeface(
                Typeface.createFromAsset(requireActivity().assets, CENTER_PATH)
            )
            pieChartRatings.setEntryLabelColor(
                ContextCompat.getColor(requireContext(), R.color.fresh_lime)
            )
            pieChartRatings.setEntryLabelTextSize(ENTRY_LABEL_TEXT_SIZE)
            pieChartRatings.transparentCircleRadius = TRANSPARENT_CIRCLE_RADIUS
            pieChartRatings.holeRadius = HOLE_RADIUS
            pieChartRatings.setCenterTextSize(CENTER_TEXT_SIZE)
            pieChartRatings.legend.isEnabled = false
            screenshotAdapter.items = game.shortScreenshots
            genresAdapter.items = game.genres
            platformsAdapter.items = game.parentPlatforms

            toolbar.setNavigationOnClickListener {
                popScreen()
            }
        }
    }

    override fun initViews(view: View) {
        with(binding) {
            recyclerViewScreenshots.adapter = screenshotAdapter
            recyclerViewScreenshots.setHasFixedSize(true)
            recyclerViewScreenshots.attachAdapter(screenshotAdapter)

            recyclerViewGenres.adapter = genresAdapter
            recyclerViewGenres.setHasFixedSize(true)
            recyclerViewGenres.attachAdapter(genresAdapter)

            recyclerViewPlatforms.adapter = platformsAdapter
            recyclerViewPlatforms.layoutManager = gridLayoutManager
            recyclerViewPlatforms.setHasFixedSize(true)
            recyclerViewPlatforms.attachAdapter(platformsAdapter)
        }
    }
}
