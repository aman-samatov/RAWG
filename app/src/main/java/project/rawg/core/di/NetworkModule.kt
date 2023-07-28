package project.rawg.core.di

import android.app.Application
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import project.rawg.core.network.Environment
import project.rawg.core.network.EnvironmentManager
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val DEFAULT_CONNECT_TIMEOUT_SECONDS = 60L
private const val DEFAULT_READ_TIMEOUT_SECONDS = 60L
private const val TAG_OKK_HTTP = "Rawg.io/OkHttp"

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideTag(): String {
        return TAG_OKK_HTTP
    }

    @Singleton
    @Provides
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(application)
    }

    @Singleton
    @Provides
    fun provideEnvironmentManager(): EnvironmentManager {
        return EnvironmentManager(Environment.getBuildVariantEnvironment())
    }

    @Singleton
    @Provides
    fun provideEnvironment(
        sharedPreferences: SharedPreferences,
        environmentManager: EnvironmentManager
    ): Environment {
        return environmentManager.loadEnvironment(sharedPreferences)
    }

    @Singleton
    @Provides
    fun provideRetrofit(environment: Environment, gson: Gson, tag: String? = null): Retrofit {
        val client = createOkHttpClient()
            .addInterceptor(createLoggingInterceptor(tag, gson))
            .build()

        return Retrofit.Builder()
            .baseUrl(environment.restAddress)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }

    private fun createLoggingInterceptor(tag: String?, gson: Gson): HttpLoggingInterceptor {
        val okHttpLogTag = if (tag.isNullOrEmpty()) "OkHttp" else tag

        val okHttpLogger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                if (!message.startsWith('{') && !message.startsWith('[')) {
                    Timber.tag(okHttpLogTag).d(message)
                    return
                }

                try {
                    val json = JsonParser().parse(message)
                    Timber.tag(okHttpLogTag).d(gson.toJson(json))
                } catch (e: Throwable) {
                    Timber.tag(okHttpLogTag).d(message)
                }
            }
        }
        return HttpLoggingInterceptor(okHttpLogger).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun createOkHttpClient(): OkHttpClient.Builder =
        OkHttpClient.Builder()
            .readTimeout(DEFAULT_CONNECT_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(DEFAULT_READ_TIMEOUT_SECONDS, TimeUnit.SECONDS)

}
