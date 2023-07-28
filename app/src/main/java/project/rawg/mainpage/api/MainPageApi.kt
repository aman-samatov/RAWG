package project.rawg.mainpage.api

import project.rawg.mainpage.api.model.GameListResponse
import project.rawg.utils.Constants.DEFAULT_PAGE
import project.rawg.utils.Constants.PAGE_SIZE
import retrofit2.http.GET
import retrofit2.http.Query


private const val KEY = "9e4288abe07843e3b6c6d3eca4c7a946"
interface MainPageApi {
    @GET("api/games")
    suspend fun getGameList(
        @Query("key") key: String = KEY,
        @Query("genres") genre: String,
        @Query("page_size") pageSize: Int = PAGE_SIZE,
        @Query("page") page: Int = DEFAULT_PAGE
    ): GameListResponse
}
