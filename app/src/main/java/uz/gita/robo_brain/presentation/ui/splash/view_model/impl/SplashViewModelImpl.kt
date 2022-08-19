package uz.gita.robo_brain.presentation.ui.splash.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.robo_brain.presentation.ui.splash.view_model.SplashViewModel

class SplashViewModelImpl : SplashViewModel, ViewModel() {

    override val openMainScreen: MutableLiveData<Unit> = MutableLiveData()

    init {
        viewModelScope.launch {
            delay(2000)
            openMainScreen.postValue(Unit)
        }
    }

}