package alisys.robotica.randomusercodetest

import alisys.robotica.viewmodel.di.VIEWMODELModule
import android.app.Application
import app.android.di.USERSModule
import app.android.network.di.APIModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                arrayListOf(
                    USERSModule,
                    VIEWMODELModule,
                    APIModule
                )
            )
        }
    }
}