package uz.gita.robo_brain.presentation.ui.input_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.robo_brain.presentation.ui.input_math.view_model.InputMathViewModel
import uz.gita.robo_brain.repository.input_math.impl.InputMathRepositoryImpl
import uz.gita.robo_brain.repository.models.InputMathData

class InputMathViewModelImpl : InputMathViewModel, ViewModel() {

    private val repository = InputMathRepositoryImpl.getInstance()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    override val timerLiveData: MutableLiveData<Int> = MutableLiveData()

    override val questionLiveData: MutableLiveData<InputMathData> = MutableLiveData()

    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData(1)

    override val answerLiveData: MutableLiveData<String> = MutableLiveData("?")

    override val minusObserver: MutableLiveData<Int> = MutableLiveData(1)

    private var time = 20

    init {
        questionLiveData.value = repository.getQuestion(1)
        viewModelScope.launch {
            while (time > 0) {
                timerLiveData.value = time
                time--
                delay(1000)
            }
            finishLiveData.value = questionCountLiveData.value ?: 1
        }
    }


    override fun check(answer: Int) {
        if (answer != questionLiveData.value?.answer) {
            val result = questionCountLiveData.value ?: 1
            finishLiveData.value = result
            repository.setBestResult(result)
            time = 0
            return
        }
        nextQuestion()
    }

    override fun clickAnswer(answer: Int) {
        var ans = answerLiveData.value ?: "0"
        if (ans == "?") {
            ans = "0"
        }
        minusObserver.value = -1

        if (ans.length < 5) {
            if (answer == -2) {
                answerLiveData.value = "-"
            } else if (answer != -1) {
                answerLiveData.value = (ans + answer).toInt().toString()
            } else {
                if (ans != "0") {
                    ans = if (ans.length == 1) {
                        minusObserver.value = 1
                        "?"
                    } else ans.substring(0, ans.lastIndex)
                    answerLiveData.value = ans
                }
            }
        } else {
            if (answer == -1) {
                ans = ans.substring(0, ans.lastIndex)
                answerLiveData.value = ans
            }
        }
    }

    override val musicLiveData: MutableLiveData<Boolean> = MutableLiveData(repository.getMusic())

    private fun nextQuestion() {
        time = 20
        timerLiveData.value = time
        questionCountLiveData.value = questionCountLiveData.value!! + 1
        answerLiveData.value = "?"
        minusObserver.value = 1
        questionLiveData.value = repository.getQuestion(questionCountLiveData.value ?: 1)
    }
}