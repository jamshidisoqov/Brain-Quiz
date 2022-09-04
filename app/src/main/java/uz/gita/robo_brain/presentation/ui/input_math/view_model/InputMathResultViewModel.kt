package uz.gita.robo_brain.presentation.ui.input_math.view_model

import androidx.lifecycle.LiveData

interface InputMathResultViewModel {

    val backLiveData: LiveData<Unit>

    val bestResultLiveData: LiveData<Int>

    val refreshLiveData: LiveData<Unit>

    fun refreshClick()

    fun backClick()

}