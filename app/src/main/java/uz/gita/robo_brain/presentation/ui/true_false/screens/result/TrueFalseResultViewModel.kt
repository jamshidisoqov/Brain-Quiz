package uz.gita.robo_brain.presentation.ui.true_false.screens.result

import androidx.lifecycle.LiveData

interface TrueFalseResultViewModel {

    val backLiveData: LiveData<Unit>

    val bestResultLiveData: LiveData<Int>

    val refreshLiveData: LiveData<Unit>

    fun refreshClick()

    fun backClick()

}