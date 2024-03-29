package uz.uni_team.robo_brain.presentation.ui.help.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.uni_team.robo_brain.presentation.ui.help.HelpViewModel
import uz.uni_team.robo_brain.repository.help.impl.HelpRepositoryImpl
import uz.uni_team.robo_brain.repository.models.HelpData

class HelpViewModelImpl : HelpViewModel, ViewModel() {

    private val repository = HelpRepositoryImpl.getInstance()

    override val helpLiveData: MutableLiveData<List<HelpData>> = MutableLiveData()

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    init {
        helpLiveData.value = repository.getHelpList()
    }

    override fun back() {
        backLiveData.value = Unit
    }
}