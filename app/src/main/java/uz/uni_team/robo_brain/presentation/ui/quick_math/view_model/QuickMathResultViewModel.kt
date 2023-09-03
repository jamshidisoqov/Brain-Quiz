package uz.uni_team.robo_brain.presentation.ui.quick_math.view_model

import androidx.lifecycle.LiveData

interface QuickMathResultViewModel {

    val backLiveData: LiveData<Unit>

    val bestResultLiveData: LiveData<Int>

    val refreshLiveData: LiveData<Unit>

    fun refreshClick()

    fun backClick()
}