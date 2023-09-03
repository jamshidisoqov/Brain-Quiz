package uz.uni_team.robo_brain.presentation.ui.help

import androidx.lifecycle.LiveData
import uz.uni_team.robo_brain.repository.models.HelpData

interface HelpViewModel {

    val helpLiveData: LiveData<List<HelpData>>

    val backLiveData: LiveData<Unit>

    fun back()
}