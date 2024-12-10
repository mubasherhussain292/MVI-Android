package co.galixo.appdev.mviBoilerPlate.app

import android.app.Application
import co.galixo.appdev.mviBoilerPlate.app.di.apiModule
import co.galixo.appdev.mviBoilerPlate.app.di.netModule
import co.galixo.appdev.mviBoilerPlate.app.di.viewModelScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            androidContext(this@App)
            modules(netModule, apiModule, viewModelScope)
        }
    }
}