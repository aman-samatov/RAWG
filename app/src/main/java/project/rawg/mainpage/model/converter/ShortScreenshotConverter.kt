package project.rawg.mainpage.model.converter

import project.rawg.core.converter.BaseDataConverter
import project.rawg.mainpage.api.model.ShortScreenshotResponse
import javax.inject.Inject

class ShortScreenshotConverter @Inject constructor(
) : BaseDataConverter<ShortScreenshotResponse, String> {
    override fun convert(response: ShortScreenshotResponse): String {
        return response.image
    }
}
