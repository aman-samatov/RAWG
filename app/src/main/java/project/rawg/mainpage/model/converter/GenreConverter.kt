package project.rawg.mainpage.model.converter

import androidx.annotation.DrawableRes
import project.rawg.R
import project.rawg.core.converter.BaseDataConverter
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.api.model.GenreResponse
import project.rawg.mainpage.model.Genre
import javax.inject.Inject

class GenreConverter @Inject constructor(
) : BaseDataConverter<GenreResponse, Genre> {
    override fun convert(response: GenreResponse): Genre {
        return Genre(
            name = response.name, image = getGenreIcon(response.slug)
        )
    }

    @DrawableRes
    private fun getGenreIcon(genreSlug: String): Int = when (genreSlug) {
        GenreType.Action.genreTitle -> R.drawable.ic_action
        GenreType.Indie.genreTitle -> R.drawable.ic_indie
        GenreType.Adventure.genreTitle -> R.drawable.ic_adventure
        GenreType.Rpg.genreTitle -> R.drawable.ic_rpg
        GenreType.Strategy.genreTitle -> R.drawable.ic_strategy
        GenreType.Shooter.genreTitle -> R.drawable.ic_shooter
        GenreType.Casual.genreTitle -> R.drawable.ic_casual
        GenreType.Simulation.genreTitle -> R.drawable.ic_simulation
        GenreType.Puzzle.genreTitle -> R.drawable.ic_puzzle
        GenreType.Arcade.genreTitle -> R.drawable.ic_arcade
        GenreType.Platformer.genreTitle -> R.drawable.ic_platformer
        GenreType.MassivelyMultiplayer.genreTitle -> R.drawable.ic_multiplayer
        GenreType.Racing.genreTitle -> R.drawable.ic_racing
        GenreType.Sports.genreTitle -> R.drawable.ic_sports
        GenreType.Fighting.genreTitle -> R.drawable.ic_fighting
        GenreType.Family.genreTitle -> R.drawable.ic_family
        GenreType.BoardGames.genreTitle -> R.drawable.ic_board
        GenreType.Educational.genreTitle -> R.drawable.ic_educational
        GenreType.Card.genreTitle -> R.drawable.ic_cards
        else -> R.drawable.ic_refresh
    }
}
