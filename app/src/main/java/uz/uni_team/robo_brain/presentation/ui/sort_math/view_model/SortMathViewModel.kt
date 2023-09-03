package uz.uni_team.robo_brain.presentation.ui.sort_math.view_model

import androidx.lifecycle.LiveData
import uz.uni_team.robo_brain.repository.models.SortedMath

interface SortMathViewModel {

    val questionLiveData: LiveData<List<SortedMath>>

    val finishLiveData:LiveData<Int>

    val timer:LiveData<Int>

    val questionCountLiveData: LiveData<Int>

    val musicLiveData:LiveData<Boolean>

    fun check(list: List<SortedMath>)

    fun nextQuestion()

}