package uz.gita.robo_brain.presentation.ui.quick_math.view_model

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.repository.models.MathData

interface QuickMathViewModel {

    val questionLiveData: LiveData<MathData>

    val finishLiveData: LiveData<Int>

    val timer: LiveData<Int>

    val questionCountLiveData: LiveData<Int>

    val musicLiveData:LiveData<Boolean>

    fun check(variant: Int)

    fun nextQuestion()
}