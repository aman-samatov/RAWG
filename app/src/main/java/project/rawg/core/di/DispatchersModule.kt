package project.rawg.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {

        @IoDispatcher
        @Provides
        fun provideContextIo(): CoroutineDispatcher = Dispatchers.IO

        @DefaultDispatcher
        @Provides
        fun provideContextDefault(): CoroutineDispatcher = Dispatchers.Default

        @Singleton
        @Provides
        fun providesCoroutineScope(
                @DefaultDispatcher defaultDispatcher: CoroutineDispatcher
        ): CoroutineScope = CoroutineScope(SupervisorJob() + defaultDispatcher)

        @Retention(AnnotationRetention.RUNTIME)
        @Qualifier
        annotation class IoDispatcher

        @Retention(AnnotationRetention.RUNTIME)
        @Qualifier
        annotation class DefaultDispatcher
}
