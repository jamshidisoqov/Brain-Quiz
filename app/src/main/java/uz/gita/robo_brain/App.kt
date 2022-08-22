package uz.gita.robo_brain

import android.app.Application
import com.tapadoo.alerter.BuildConfig
import timber.log.Timber
import timber.log.Timber.Forest.plant
import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl


// Created by Jamshid Isoqov an 8/21/2022
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPrefImpl.init(this)
        if (BuildConfig.DEBUG) {
            plant(Timber.DebugTree())
        }
    }

}