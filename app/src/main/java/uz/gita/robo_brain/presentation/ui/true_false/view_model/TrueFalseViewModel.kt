package uz.gita.robo_brain.presentation.ui.true_false.view_model

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.repository.models.TrueFalseData

interface TrueFalseViewModel {

    val questionLiveData: LiveData<TrueFalseData>

    val finishLiveData: LiveData<Int>

    val timer: LiveData<Int>

    val questionCountLiveData: LiveData<Int>

    fun check(boolean: Boolean)

    fun nextQuestion()

}