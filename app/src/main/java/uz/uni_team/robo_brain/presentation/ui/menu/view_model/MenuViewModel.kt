package uz.uni_team.robo_brain.presentation.ui.menu.view_model

import androidx.lifecycle.LiveData

interface MenuViewModel {

    val openPuzzle15LiveData: LiveData<Unit>

    val openQuickMathLiveData: LiveData<Unit>

    val openTrueFalseLiveData: LiveData<Unit>

    val openInputMathLiveData: LiveData<Unit>

    val openSortedMathLiveData: LiveData<Unit>

    val openTableOfGrowLiveData: LiveData<Unit>

    val openPuzzle2048LiveData: LiveData<Unit>

    val openHardMathLiveData: LiveData<Unit>

    val openSupportLiveData:LiveData<Unit>

    val openHelpLiveData:LiveData<Unit>

    val imageLiveData:LiveData<String>


    fun openPuzzle15()

    fun openQuickMath()

    fun openTrueFalse()

    fun openInputMath()

    fun openSortedMath()

    fun openTableOfGrow()

    fun openPuzzle2048()

    fun openHardMath()

    fun openSupport()

    fun openHelp()

}