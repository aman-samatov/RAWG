package project.rawg.mainpage.ui.model.game

import project.rawg.mainpage.api.PagingState
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.model.GameCategoryModel

private const val START_ITEM = 1
private const val END_ITEM = 3

object GameListConverter {

    private val initialProgressRage = IntRange(start = START_ITEM, endInclusive = END_ITEM)

    fun toGameListUi(model: GameCategoryModel): GameListUi =
        when (model.dataState) {
            is PagingState.Initial ->
                GameListUi(
                    genre = model.genreType,
                    results = initialProgressRage
                        .map { ProgressGame }
                )

            is PagingState.Content ->
                GameListUi(
                    genre = model.genreType,
                    results = model.dataState.data.toGameUiList()
                )

            is PagingState.Paging ->
                GameListUi(
                    genre = model.genreType,
                    results = model.dataState.availableContent.toGameUiList()
                        .plus(LoadingProgress)
                )

            is PagingState.Persist ->
                GameListUi(
                    genre = model.genreType,
                    results = model.dataState.data.toGameUiList()
                        .plus(ErrorItem(model.genreType))
                )

            is PagingState.Error ->
                GameListUi(
                    genre = model.genreType,
                    results = listOf(ErrorItem(model.genreType))
                )

        }

    private fun List<Game>.toGameUiList() = map {
        GameUi(
            id = it.id,
            slug = it.slug,
            name = it.name,
            released = it.released,
            backgroundImage = it.backgroundImage,
            rating = it.rating,
            ratings = it.ratings.map { rating ->
                rating.toRatingUi()
            },
            ratingsCount = it.ratingsCount,
            metacritic = it.metacritic.toString(),
            parentPlatforms = it.parentPlatforms.map { platform ->
                platform.toPlatformUi()
            },
            genres = it.genres.map { genre ->
                genre.toGenreUi()
            },
            esrbRatingName = it.esrbRatingName,
            esrbRatingIcon = it.esrbRatingIcon,
            shortScreenshots = it.shortScreenshots
        )
    }
}