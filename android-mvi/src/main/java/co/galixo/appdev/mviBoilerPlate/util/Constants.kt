package co.galixo.appdev.mviBoilerPlate.util

object Constants {
    val SPLASH_TIME_OUT = 1500L // Seconds
    val AppCenter_Secret = "APPCENTER_APP_SECRET_KEY"
    val Base_URL = "https://api.github.com/"

    enum class PersistenceKey {
        IS_LOGIN,
    }
}
