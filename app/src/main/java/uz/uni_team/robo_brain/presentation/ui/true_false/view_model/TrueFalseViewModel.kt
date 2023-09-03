package uz.uni_team.robo_brain.presentation.ui.true_false.view_model

import androidx.lifecycle.LiveData
import uz.uni_team.robo_brain.repository.models.TrueFalseData

interface TrueFalseViewModel {

    val questionLiveData: LiveData<TrueFalseData>

    val finishLiveData: LiveData<Int>

    val timer: LiveData<Int>

    val questionCountLiveData: LiveData<Int>

    val musicLiveData:LiveData<Boolean>

    fun check(boolean: Boolean)

    fun nextQuestion()

}