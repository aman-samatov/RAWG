package project.rawg.detailspage.ui.adapter

import coil.load
import coil.transform.RoundedCornersTransformation
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import project.rawg.databinding.ItemGenrePlatformBinding
import project.rawg.databinding.ItemScreenshotBinding
import project.rawg.mainpage.ui.model.game.GenreUi
import project.rawg.mainpage.ui.model.game.PlatformUi
import project.rawg.utils.extensions.setThrottleOnClickListener

object DetailsPageDelegates {

    fun screenshotDelegate(onScreenshotClick: (String) -> Unit) =
        adapterDelegateViewBinding(
            { inflater, container -> ItemScreenshotBinding.inflate(inflater, container, false) }
        ) {
            bind {
                with(binding.imageViewScreenshot) {
                    load(item) {
                        crossfade(true)
                        crossfade(600)
                        transformations(RoundedCornersTransformation(16f))
                    }
                    setThrottleOnClickListener { onScreenshotClick.invoke(item) }
                }
            }
        }

    fun genreDelegate() = adapterDelegateViewBinding<GenreUi, GenreUi, ItemGenrePlatformBinding>(
        { inflater, container -> ItemGenrePlatformBinding.inflate(inflater, container, false) }
    ) {
        bind {
            with(binding) {
                imageViewIcon.load(item.image)
                textViewName.text = item.name
            }
        }
    }

    fun platformDelegate() =
        adapterDelegateViewBinding<PlatformUi, PlatformUi, ItemGenrePlatformBinding>(
            { inflater, container -> ItemGenrePlatformBinding.inflate(inflater, container, false) }
        ) {
            bind {
                with(binding) {
                    imageViewIcon.load(item.image)
                    textViewName.text = item.name
                }
            }
        }
}
