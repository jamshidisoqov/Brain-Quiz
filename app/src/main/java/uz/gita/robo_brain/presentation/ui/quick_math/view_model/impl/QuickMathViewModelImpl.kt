package uz.gita.robo_brain.presentation.ui.quick_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.robo_brain.presentation.ui.quick_math.view_model.QuickMathViewModel
import uz.gita.robo_brain.repository.models.MathData
import uz.gita.robo_brain.repository.quick_math.impl.QuickMathRepositoryImpl

class QuickMathViewModelImpl : QuickMathViewModel, ViewModel() {

    private val repository = QuickMathRepositoryImpl.getInstance()

    override val questionLiveData: MutableLiveData<MathData> = MutableLiveData()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    override val timer: MutableLiveData<Int> = MutableLiveData()

    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData(1)

    var time = 10

    init {
        questionLiveData.value = repository.getQuestion(1)
        viewModelScope.launch {
            while (time > 0) {
                timer.value = time
                time--
                delay(1000)
            }
            finishLiveData.value = questionCountLiveData.value ?: 1
        }
    }


    override fun check(variant: Int) {
        val answer = questionLiveData.value?.answer
        val result = questionCountLiveData.value ?: 1
        if (answer != variant) {
            finishLiveData.value = result
            repository.setBestResult(result)
            time = 0
            return
        }
        nextQuestion()
    }

    override fun nextQuestion() {
        time = 10
        timer.value = time
        questionCountLiveData.value = questionCountLiveData.value!! + 1
        questionLiveData.value = repository.getQuestion(questionCountLiveData.value ?: 1)
    }
}