package uz.gita.robo_brain.presentation.ui.sort_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.gita.robo_brain.repository.models.SortedMath
import uz.gita.robo_brain.repository.sorted_math.impl.SortedMathRepositoryImpl

class SortMathViewModelImpl : SortMathViewModel, ViewModel() {


    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData(0)

    private val repository = SortedMathRepositoryImpl.getInstance()

    override val questionLiveData: MutableLiveData<List<SortedMath>> = MutableLiveData()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    override val gameOverLiveData: MutableLiveData<Unit> = MutableLiveData()


    init {
        questionCountLiveData.value = 0
        nextQuestion()
    }


    override fun check(list: List<SortedMath>) {
        val currentQuestion = questionLiveData.value!!
        for (i in list.indices) {
            if (list[i].order != currentQuestion[i].order) {
                finishLiveData.postValue(questionCountLiveData.value)
                return
            }
        }
        nextQuestion()
    }

    override fun nextQuestion() {
        questionLiveData.postValue(repository.getQuestions(if (questionCountLiveData.value == null) 1 else questionCountLiveData.value!!))
        questionCountLiveData.value = questionCountLiveData.value!! + 1
    }
}