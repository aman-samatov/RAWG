package project.rawg.mainpage.di

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import project.rawg.core.converter.BaseDataConverter
import project.rawg.mainpage.api.GenreType
import project.rawg.mainpage.api.MainPageApi
import project.rawg.mainpage.api.model.GameResponse
import project.rawg.mainpage.db.dao.GamesDao
import project.rawg.mainpage.db.database.GamesDataBase
import project.rawg.mainpage.db.database.GamesMediator
import project.rawg.mainpage.db.model.converter.GenreTypeConverter
import project.rawg.mainpage.interactor.MainPageInteractor
import project.rawg.mainpage.interactor.MainPageInteractorImpl
import project.rawg.mainpage.model.Game
import project.rawg.mainpage.model.converter.GameConverter
import project.rawg.mainpage.model.converter.ParentPlatformConverter
import project.rawg.mainpage.model.converter.RatingConverter
import project.rawg.mainpage.model.converter.ShortScreenshotConverter
import project.rawg.mainpage.repository.MainPageLocalRepositoryImpl
import project.rawg.mainpage.repository.MainPageRemoteRepositoryImpl
import project.rawg.mainpage.repository.base.MainPageLocalRepository
import project.rawg.mainpage.repository.base.MainPageRemoteRepository
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MainPageModule {

    @Binds
    @Singleton
    abstract fun bindRemoteRepository(repository: MainPageRemoteRepositoryImpl): MainPageRemoteRepository

    @Binds
    @Singleton
    abstract fun bindLocalRepository(repository: MainPageLocalRepositoryImpl): MainPageLocalRepository

    @Binds
    @Singleton
    abstract fun bindInteractor(interactor: MainPageInteractorImpl): MainPageInteractor

    @Binds
    @Singleton
    abstract fun bindGameConverter(gameConverter: GameConverter): BaseDataConverter<GameResponse, Game>

    companion object {
        @Provides
        @Singleton
        fun provideApi(retrofit: Retrofit): MainPageApi =
            retrofit.create(MainPageApi::class.java)

        @Provides
        @Singleton
        fun provideChannelDao(appDatabase: GamesDataBase): GamesDao =
            appDatabase.gamesDao

        @Provides
        @Singleton
        fun provideAppDatabase(@ApplicationContext appContext: Context): GamesDataBase =
            Room.databaseBuilder(
                appContext,
                GamesDataBase::class.java,
                "Database"
            ).build()

        @Provides
        @Singleton
        fun provideGenreConverter() = GenreTypeConverter()

        @Provides
        @Singleton
        fun providePlatformConverter() = ParentPlatformConverter()

        @Provides
        @Singleton
        fun provideRatingConverter() = RatingConverter()

        @Provides
        @Singleton
        fun provideScreenshotConverter() = ShortScreenshotConverter()

        @Provides
        @Singleton
        @Named("action")
        fun actionMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Action,
            scope
        )

        @Provides
        @Singleton
        @Named("indie")
        fun indieMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Indie,
            scope
        )

        @Provides
        @Singleton
        @Named("adventure")
        fun adventureMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Adventure,
            scope
        )

        @Provides
        @Singleton
        @Named("rpg")
        fun rpgMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Rpg,
            scope
        )

        @Provides
        @Singleton
        @Named("strategy")
        fun strategyMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Strategy,
            scope
        )

        @Provides
        @Singleton
        @Named("shooter")
        fun shooterMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Shooter,
            scope
        )

        @Provides
        @Singleton
        @Named("casual")
        fun casualMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Casual,
            scope
        )

        @Provides
        @Singleton
        @Named("simulation")
        fun simulationMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Simulation,
            scope
        )

        @Provides
        @Singleton
        @Named("puzzle")
        fun puzzleMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Puzzle,
            scope
        )

        @Provides
        @Singleton
        @Named("arcade")
        fun arcadeMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Arcade,
            scope
        )

        @Provides
        @Singleton
        @Named("platformer")
        fun platformerMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Platformer,
            scope
        )

        @Provides
        @Singleton
        @Named("massive")
        fun massiveMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.MassivelyMultiplayer,
            scope
        )

        @Provides
        @Singleton
        @Named("racing")
        fun racingMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Racing,
            scope
        )

        @Provides
        @Singleton
        @Named("sports")
        fun sportsMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Sports,
            scope
        )

        @Provides
        @Singleton
        @Named("fighting")
        fun fightingMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Fighting,
            scope
        )

        @Provides
        @Singleton
        @Named("family")
        fun familyMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Family,
            scope
        )

        @Provides
        @Singleton
        @Named("board")
        fun boardMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.BoardGames,
            scope
        )

        @Provides
        @Singleton
        @Named("edu")
        fun eduMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Educational,
            scope
        )

        @Provides
        @Singleton
        @Named("card")
        fun cardMediator(
            remoteRepository: MainPageRemoteRepository,
            localRepository: MainPageLocalRepository,
            scope: CoroutineScope
        ): GamesMediator = GamesMediator(
            remoteRepository,
            localRepository,
            GenreType.Card,
            scope
        )
    }
}
