package uz.gita.robo_brain.presentation.ui.home.view_model

import androidx.lifecycle.LiveData

interface HomeViewModel {

    val openMainLiveData: LiveData<Unit>

    val openPlayMarket: LiveData<Unit>

    val openShareAppLiveData: LiveData<Unit>

    val openSettingLiveData: LiveData<Unit>


    val openAboutLiveData:LiveData<Unit>

    fun openMain()

    fun openPlayMarket()

    fun openShareApp()

    fun openSettings()

    fun openAbout()

}