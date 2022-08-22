package uz.gita.robo_brain.presentation.ui.sort_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.gita.robo_brain.repository.models.SortedMath
import uz.gita.robo_brain.repository.sorted_math.impl.SortedMathRepositoryImpl

class SortMathViewModelImpl : SortMathViewModel, ViewModel() {

    private val repository = SortedMathRepositoryImpl.getInstance()

    override val questionLiveData: MutableLiveData<List<SortedMath>> = MutableLiveData()

    override val checkLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    override val gameOverLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData(1)

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
        questionLiveData.postValue(repository.getQuestions(questionCountLiveData.value ?: 1))
        questionCountLiveData.value = questionCountLiveData.value!! + 1
    }
}