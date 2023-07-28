package project.rawg.mainpage.model.converter

import androidx.annotation.DrawableRes
import project.rawg.R
import project.rawg.core.converter.BaseDataConverter
import project.rawg.mainpage.api.PlatformType
import project.rawg.mainpage.api.model.ParentPlatformResponse
import project.rawg.mainpage.model.Platform
import javax.inject.Inject

class ParentPlatformConverter @Inject constructor(
) : BaseDataConverter<ParentPlatformResponse, Platform> {
    override fun convert(response: ParentPlatformResponse): Platform {
        return Platform(
            name = response.parentPlatformInfo.name,
            image = getPlatformIcon(response.parentPlatformInfo.slug)
        )
    }

    @DrawableRes
    private fun getPlatformIcon(platformSlug: String): Int = when (platformSlug) {
        PlatformType.ANDROID.platformName -> R.drawable.ic_android
        PlatformType.IOS.platformName -> R.drawable.ic_apple
        PlatformType.PC.platformName -> R.drawable.ic_computer
        PlatformType.LINUX.platformName -> R.drawable.ic_linux
        PlatformType.MAC.platformName -> R.drawable.ic_macos
        PlatformType.NINTENDO.platformName -> R.drawable.ic_nintendo
        PlatformType.PLAYSTATION.platformName -> R.drawable.ic_playstation
        PlatformType.XBOX.platformName -> R.drawable.ic_xbox
        PlatformType.SEGA.platformName -> R.drawable.ic_sega

        else -> R.drawable.ic_platform
    }
}
