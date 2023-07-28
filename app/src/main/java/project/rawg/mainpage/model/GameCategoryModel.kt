package project.rawg.mainpage.model

import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.api.PagingState

data class GameCategoryModel(
    val genreType: GenreType,
    val dataState: PagingState<List<Game>>
)
