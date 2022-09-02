package uz.gita.robo_brain.presentation.ui.sort_math.screens.result

import androidx.lifecycle.LiveData

interface SortedMathResultViewModel {

    val backLiveData: LiveData<Unit>

    val bestResultLiveData: LiveData<Int>

    val refreshLiveData: LiveData<Unit>

    fun refreshClick()

    fun backClick()

}