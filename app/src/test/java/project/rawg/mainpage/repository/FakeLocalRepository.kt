package project.rawg.mainpage.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.repository.base.MainPageLocalRepository


class FakeLocalRepository: MainPageLocalRepository{

    private val gameList = mutableListOf<Game>()
    private val gameFlow = MutableStateFlow<List<Game>>(gameList)

    override suspend fun getGamesList(genre: String): Flow<List<Game>> {
        return gameFlow
    }

    override suspend fun putGamesList(data: List<Game>, genre: String) {
        gameList.addAll(data)
        gameFlow.tryEmit(gameList)
    }
}