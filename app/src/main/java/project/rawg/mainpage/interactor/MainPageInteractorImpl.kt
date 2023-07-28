package project.rawg.mainpage.interactor

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.db.database.GamesMediator
import project.rawg.mainpage.model.*
import project.rawg.mainpage.ui.model.*
import project.rawg.mainpage.ui.model.base.BaseGameList
import project.rawg.mainpage.ui.model.game.*
import javax.inject.Inject
import javax.inject.Named

class MainPageInteractorImpl @Inject constructor(
    @Named("action") private val actionMediator: GamesMediator,
    @Named("indie") private val indieMediator: GamesMediator,
    @Named("adventure") private val adventureMediator: GamesMediator,
    @Named("rpg") private val rpgMediator: GamesMediator,
    @Named("strategy") private val strategyMediator: GamesMediator,
    @Named("shooter") private val shooterMediator: GamesMediator,
    @Named("casual") private val casualMediator: GamesMediator,
    @Named("simulation") private val simulationMediator: GamesMediator,
    @Named("puzzle") private val puzzleMediator: GamesMediator,
    @Named("arcade") private val arcadeMediator: GamesMediator,
    @Named("platformer") private val platformerMediator: GamesMediator,
    @Named("massive") private val massivelyMultiplayerMediator: GamesMediator,
    @Named("racing") private val racingMediator: GamesMediator,
    @Named("sports") private val sportsMediator: GamesMediator,
    @Named("fighting") private val fightingMediator: GamesMediator,
    @Named("family") private val familyMediator: GamesMediator,
    @Named("board") private val boardGamesMediator: GamesMediator,
    @Named("edu") private val educationalMediator: GamesMediator,
    @Named("card") private val cardMediator: GamesMediator
) : MainPageInteractor {
    override fun data(): Flow<List<BaseGameList>> = combine(
        actionMediator.data(),
        indieMediator.data(),
        adventureMediator.data(),
        rpgMediator.data(),
        strategyMediator.data(),
        shooterMediator.data(),
        casualMediator.data(),
        simulationMediator.data(),
        puzzleMediator.data(),
        arcadeMediator.data(),
        platformerMediator.data(),
        massivelyMultiplayerMediator.data(),
        racingMediator.data(),
        sportsMediator.data(),
        fightingMediator.data(),
        familyMediator.data(),
        boardGamesMediator.data(),
        educationalMediator.data(),
        cardMediator.data()
    ) { values ->
        values.map { gameCategory ->
            GameListConverter.toGameListUi(gameCategory)
        }
    }

    override suspend fun initCategory(genre: GenreType) {
        when (genre) {
            is GenreType.Action -> actionMediator.init()
            is GenreType.Indie -> indieMediator.init()
            is GenreType.Adventure -> adventureMediator.init()
            is GenreType.Rpg -> rpgMediator.init()
            is GenreType.Strategy -> strategyMediator.init()
            is GenreType.Shooter -> shooterMediator.init()
            is GenreType.Casual -> casualMediator.init()
            is GenreType.Simulation -> simulationMediator.init()
            is GenreType.Puzzle -> puzzleMediator.init()
            is GenreType.Arcade -> arcadeMediator.init()
            is GenreType.Platformer -> platformerMediator.init()
            is GenreType.MassivelyMultiplayer -> massivelyMultiplayerMediator.init()
            is GenreType.Racing -> racingMediator.init()
            is GenreType.Sports -> sportsMediator.init()
            is GenreType.Fighting -> fightingMediator.init()
            is GenreType.Family -> familyMediator.init()
            is GenreType.BoardGames -> boardGamesMediator.init()
            is GenreType.Educational -> educationalMediator.init()
            is GenreType.Card -> cardMediator.init()
        }
    }

    override suspend fun tryToLoadMore(genre: GenreType, index: Int) {
        when (genre) {
            is GenreType.Action -> actionMediator.tryToLoadMore(index)
            is GenreType.Indie -> indieMediator.tryToLoadMore(index)
            is GenreType.Adventure -> adventureMediator.tryToLoadMore(index)
            is GenreType.Rpg -> rpgMediator.tryToLoadMore(index)
            is GenreType.Strategy -> strategyMediator.tryToLoadMore(index)
            is GenreType.Shooter -> shooterMediator.tryToLoadMore(index)
            is GenreType.Casual -> casualMediator.tryToLoadMore(index)
            is GenreType.Simulation -> simulationMediator.tryToLoadMore(index)
            is GenreType.Puzzle -> puzzleMediator.tryToLoadMore(index)
            is GenreType.Arcade -> arcadeMediator.tryToLoadMore(index)
            is GenreType.Platformer -> platformerMediator.tryToLoadMore(index)
            is GenreType.MassivelyMultiplayer -> massivelyMultiplayerMediator.tryToLoadMore(index)
            is GenreType.Racing -> racingMediator.tryToLoadMore(index)
            is GenreType.Sports -> sportsMediator.tryToLoadMore(index)
            is GenreType.Fighting -> fightingMediator.tryToLoadMore(index)
            is GenreType.Family -> familyMediator.tryToLoadMore(index)
            is GenreType.BoardGames -> boardGamesMediator.tryToLoadMore(index)
            is GenreType.Educational -> educationalMediator.tryToLoadMore(index)
            is GenreType.Card -> cardMediator.tryToLoadMore(index)
        }
    }

    override suspend fun refresh(genre: GenreType) {
        when (genre) {
            is GenreType.Action -> actionMediator.refresh()
            is GenreType.Indie -> indieMediator.refresh()
            is GenreType.Adventure -> adventureMediator.refresh()
            is GenreType.Rpg -> rpgMediator.refresh()
            is GenreType.Strategy -> strategyMediator.refresh()
            is GenreType.Shooter -> shooterMediator.refresh()
            is GenreType.Casual -> casualMediator.refresh()
            is GenreType.Simulation -> simulationMediator.refresh()
            is GenreType.Puzzle -> puzzleMediator.refresh()
            is GenreType.Arcade -> arcadeMediator.refresh()
            is GenreType.Platformer -> platformerMediator.refresh()
            is GenreType.MassivelyMultiplayer -> massivelyMultiplayerMediator.refresh()
            is GenreType.Racing -> racingMediator.refresh()
            is GenreType.Sports -> sportsMediator.refresh()
            is GenreType.Fighting -> fightingMediator.refresh()
            is GenreType.Family -> familyMediator.refresh()
            is GenreType.BoardGames -> boardGamesMediator.refresh()
            is GenreType.Educational -> educationalMediator.refresh()
            is GenreType.Card -> cardMediator.refresh()
        }
    }
}
