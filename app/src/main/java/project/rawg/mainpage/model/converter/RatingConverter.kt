package project.rawg.mainpage.model.converter

import project.rawg.core.converter.BaseDataConverter
import project.rawg.mainpage.api.model.RatingResponse
import project.rawg.mainpage.model.Rating
import javax.inject.Inject

class RatingConverter @Inject constructor(
) : BaseDataConverter<RatingResponse, Rating> {
    override fun convert(response: RatingResponse): Rating {
        return Rating(
            id = response.id,
            title = response.title,
            count = response.count,
            percent = response.percent
        )
    }
}
