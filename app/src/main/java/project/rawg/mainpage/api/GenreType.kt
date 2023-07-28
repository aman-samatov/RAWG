package project.rawg.mainpage.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
sealed class GenreType(val genreTitle: String) : Parcelable {
    object Action : GenreType("action")
    object Indie : GenreType("indie")
    object Adventure : GenreType("adventure")
    object Rpg : GenreType("role-playing-games-rpg")
    object Strategy : GenreType("strategy")
    object Shooter : GenreType("shooter")
    object Casual : GenreType("casual")
    object Simulation : GenreType("simulation")
    object Puzzle : GenreType("puzzle")
    object Arcade : GenreType("arcade")
    object Platformer : GenreType("platformer")
    object MassivelyMultiplayer : GenreType("massively-multiplayer")
    object Racing : GenreType("racing")
    object Sports : GenreType("sports")
    object Fighting : GenreType("fighting")
    object Family : GenreType("family")
    object BoardGames : GenreType("board-games")
    object Educational : GenreType("educational")
    object Card : GenreType("card")
}
