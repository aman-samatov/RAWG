package project.rawg.core.network

import android.content.SharedPreferences
import javax.inject.Inject

private const val KEY_BASE_URL = "KEY_BASE_URL"
private const val SSL_ENABLED_KEY = "SSL_ENABLED_KEY"

class EnvironmentManager @Inject constructor(
    private val environment: Environment
) {

    fun loadEnvironment(
        sharedPreferences: SharedPreferences
    ) = with(sharedPreferences) {
        Environment(
            baseAddress = getString(KEY_BASE_URL, environment.baseAddress)!!,
            isSslEnabled = getBoolean(SSL_ENABLED_KEY, environment.isSslEnabled)
        )
    }
}