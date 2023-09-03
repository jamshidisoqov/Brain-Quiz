package uz.uni_team.robo_brain.presentation.ui.settings.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.uni_team.robo_brain.data.pref.impl.SharedPrefImpl
import uz.uni_team.robo_brain.presentation.ui.settings.SettingsViewModel

class SettingsViewModelImpl : SettingsViewModel, ViewModel() {
    private var sharedPreferences = SharedPrefImpl.getInstance()

    override val nameLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreferences.getName())

    override val imageLiveData: MutableLiveData<String> =
        MutableLiveData(sharedPreferences.getImageUri())

    override val musicLiveData: MutableLiveData<Boolean> =
        MutableLiveData(sharedPreferences.getMusic())

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeNameLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val changeImageLiveData: MutableLiveData<Unit> = MutableLiveData()



    override fun changeName() {
        changeNameLiveData.postValue(Unit)
    }

    override fun changeImage() {
        changeImageLiveData.postValue(Unit)
    }

    override fun setName(name: String) {
        sharedPreferences.setName(name)
        nameLiveData.postValue(name)
    }


    override fun setImage(str: String) {
        sharedPreferences.setImageUri(str)
        imageLiveData.postValue(str)
    }

    override fun getMusic(): Boolean = sharedPreferences.getMusic()

    override fun setMusic(music: Boolean) = sharedPreferences.setMusic(music)

    override fun backClick() {
        backLiveData.postValue(Unit)
    }
}