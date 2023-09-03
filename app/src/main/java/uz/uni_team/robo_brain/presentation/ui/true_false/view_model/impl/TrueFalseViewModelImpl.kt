package uz.uni_team.robo_brain.presentation.ui.true_false.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.uni_team.robo_brain.presentation.ui.true_false.view_model.TrueFalseViewModel
import uz.uni_team.robo_brain.repository.models.TrueFalseData
import uz.uni_team.robo_brain.repository.true_false.impl.TrueFalseRepositoryImpl

class TrueFalseViewModelImpl : TrueFalseViewModel, ViewModel() {

    private val repository = TrueFalseRepositoryImpl.getInstance()

    override val questionLiveData: MutableLiveData<TrueFalseData> = MutableLiveData()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    override val timer: MutableLiveData<Int> = MutableLiveData()

    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData(1)
    override val musicLiveData:MutableLiveData<Boolean> = MutableLiveData(repository.getMusic())

    private var time = 10

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

    override fun check(boolean: Boolean) {
        if (questionLiveData.value?.answer != boolean) {
            val result = questionCountLiveData.value?:1
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
        questionCountLiveData.value = questionCountLiveData.value!!+1
        questionLiveData.value = repository.getQuestion(questionCountLiveData.value ?: 1)
    }
}