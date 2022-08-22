package uz.gita.robo_brain.presentation.ui.sort_math.view_model

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.repository.models.SortedMath

interface SortMathViewModel {

    val questionLiveData: LiveData<List<SortedMath>>

    val checkLiveData: LiveData<Unit>

    val gameOverLiveData: LiveData<Unit>

    val questionCountLiveData: LiveData<Int>

    fun check(list: List<SortedMath>)

    fun nextQuestion()

}