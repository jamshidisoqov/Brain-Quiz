package uz.gita.robo_brain.presentation.ui.sort_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.gita.robo_brain.repository.models.SortedMath
import uz.gita.robo_brain.repository.sorted_math.impl.SortedMathRepositoryImpl

class SortMathViewModelImpl : SortMathViewModel, ViewModel() {

    private val repository = SortedMathRepositoryImpl.getInstance()

    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData(0)

    override val musicLiveData: MutableLiveData<Boolean> = MutableLiveData(repository.getMusic())


    override val questionLiveData: MutableLiveData<List<SortedMath>> = MutableLiveData()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    var time = 30

    override val timer: MutableLiveData<Int> = MutableLiveData(time)


    init {
        questionCountLiveData.value = 0
        nextQuestion()
        viewModelScope.launch {
            while (time > 0) {
                delay(1000)
                time--
                timer.value = time
            }
            finishLiveData.value = questionCountLiveData.value
        }
    }


    override fun check(list: List<SortedMath>) {
        val currentQuestion = questionLiveData.value!!
        for (i in list.indices) {
            if (list[i].order != currentQuestion[i].order) {
                val result = questionCountLiveData.value ?: 1
                finishLiveData.postValue(result)
                time = 1
                repository.setBestResult(result)
                return
            }
        }
        time = 30
        nextQuestion()
    }

    override fun nextQuestion() {
        questionLiveData.postValue(repository.getQuestions(if (questionCountLiveData.value == null) 1 else questionCountLiveData.value!!))
        questionCountLiveData.value = questionCountLiveData.value!! + 1
    }
}