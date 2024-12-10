package co.galixo.appdev.mviBoilerPlate.view.Splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import co.galixo.appdev.mviBoilerPlate.R
import co.galixo.appdev.mviBoilerPlate.util.Constants
import co.galixo.appdev.mviBoilerPlate.view.MainActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import io.reactivex.Maybe
import java.util.concurrent.TimeUnit

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Initialize AppCenter
        AppCenter.start(
            application, Constants.AppCenter_Secret,
            Analytics::class.java, Crashes::class.java
        )
        Maybe.empty<Any>()
            .delay(Constants.SPLASH_TIME_OUT, TimeUnit.MILLISECONDS)
            .doOnComplete {
                startActivity(MainActivity.getStartIntent(this))
                finish()
            }.subscribe()
    }
}