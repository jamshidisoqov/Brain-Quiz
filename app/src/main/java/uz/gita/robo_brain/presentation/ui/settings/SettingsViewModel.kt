package uz.gita.robo_brain.presentation.ui.settings

import androidx.lifecycle.LiveData

interface SettingsViewModel {

    val nameLiveData: LiveData<String>

    val imageLiveData: LiveData<String>

    val backLiveData: LiveData<Unit>

    val changeNameLiveData: LiveData<Unit>

    val changeImageLiveData: LiveData<Unit>

    val musicLiveData:LiveData<Boolean>

    fun changeName()

    fun changeImage()

    fun setName(name: String)

    fun backClick()

    fun setImage(str: String)

    fun getMusic(): Boolean

    fun setMusic(music: Boolean)

}