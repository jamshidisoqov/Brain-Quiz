package uz.gita.robo_brain.presentation.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AboutViewModel : ViewModel() {

    private var _backLiveData: MutableLiveData<Unit> = MutableLiveData()
    val backLiveData: LiveData<Unit> = _backLiveData

    fun onBack() {
        _backLiveData.postValue(Unit)
    }
}