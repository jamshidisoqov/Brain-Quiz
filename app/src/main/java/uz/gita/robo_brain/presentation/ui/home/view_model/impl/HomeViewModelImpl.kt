package uz.gita.robo_brain.presentation.ui.home.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.data.pref.impl.SharedPrefImpl
import uz.gita.robo_brain.presentation.ui.home.view_model.HomeViewModel

class HomeViewModelImpl : HomeViewModel, ViewModel() {

    override val openMainLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openPlayMarket: MutableLiveData<Unit> = MutableLiveData()

    override val openShareAppLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openSettingLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val openAboutLiveData: MutableLiveData<Unit> = MutableLiveData()

    override fun openMain() {
        openMainLiveData.postValue(Unit)
    }

    override fun openPlayMarket() {
        openPlayMarket.postValue(Unit)
    }

    override fun openShareApp() {
        openShareAppLiveData.postValue(Unit)
    }

    override fun openSettings() {
        openSettingLiveData.postValue(Unit)
    }

    override fun openAbout() {
        openAboutLiveData.value = Unit
    }
}