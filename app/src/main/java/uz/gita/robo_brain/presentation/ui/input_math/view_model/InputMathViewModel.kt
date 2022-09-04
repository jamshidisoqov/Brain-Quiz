package uz.gita.robo_brain.presentation.ui.input_math.view_model

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.repository.models.InputMathData

interface InputMathViewModel {

    val finishLiveData: LiveData<Int>

    val timerLiveData: LiveData<Int>

    val questionLiveData: LiveData<InputMathData>

    val questionCountLiveData: LiveData<Int>

    val answerLiveData: LiveData<String>

    val minusObserver:LiveData<Int>

    fun check(answer: Int)

    fun clickAnswer(answer: Int)

}