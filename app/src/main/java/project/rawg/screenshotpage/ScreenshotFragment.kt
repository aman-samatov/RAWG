package project.rawg.screenshotpage

import android.view.View
import coil.load
import project.rawg.R
import project.rawg.core.mvvm.BaseFragment
import project.rawg.databinding.FragmentScreenshotBinding
import project.rawg.utils.Arguments
import project.rawg.utils.extensions.args
import project.rawg.utils.extensions.popScreen
import project.rawg.utils.extensions.setThrottleOnClickListener
import project.rawg.utils.extensions.withArgs
import project.rawg.utils.viewbinding.viewBinding


class ScreenshotFragment : BaseFragment(R.layout.fragment_screenshot) {

    private val binding: FragmentScreenshotBinding by viewBinding()

    companion object {
        fun newInstance(screenshot: String) =
            ScreenshotFragment().withArgs(Arguments.SCREENSHOT_DATA to screenshot)
    }

    private val screenshot: String by args(Arguments.SCREENSHOT_DATA)

    override fun bind() {
        binding.imageViewScreenshot.load(screenshot)
    }

    override fun initViews(view: View) {
        binding.layout.setThrottleOnClickListener {
            popScreen()
        }
    }
}
