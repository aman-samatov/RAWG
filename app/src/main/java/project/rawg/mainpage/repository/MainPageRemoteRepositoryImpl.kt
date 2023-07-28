package project.rawg.mainpage.repository

import project.rawg.core.converter.BaseDataConverter
import project.rawg.core.converter.convertList
import project.rawg.mainpage.api.MainPageApi
import project.rawg.mainpage.api.model.GameResponse
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.repository.base.MainPageRemoteRepository
import javax.inject.Inject

class MainPageRemoteRepositoryImpl @Inject constructor(
    private val api: MainPageApi,
    private val gameConverter: BaseDataConverter<GameResponse, Game>
) : MainPageRemoteRepository {

    override suspend fun initialLoading(genre: String): List<Game> {
        val response = api.getGameList(genre = genre)
        return response.results.convertList(gameConverter)
    }

    override suspend fun loadMore(genre: String, page: Int): List<Game> {
        val response = api.getGameList(genre = genre, page = page + 1)
        return response.results.convertList(gameConverter)
    }
}
