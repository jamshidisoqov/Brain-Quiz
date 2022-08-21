package uz.gita.robo_brain

import android.app.Application
import com.tapadoo.alerter.BuildConfig
import timber.log.Timber
import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl

// Created by Jamshid Isoqov an 8/21/2022
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.asTree())
        }
        SharedPrefImpl.init(this)
    }

}