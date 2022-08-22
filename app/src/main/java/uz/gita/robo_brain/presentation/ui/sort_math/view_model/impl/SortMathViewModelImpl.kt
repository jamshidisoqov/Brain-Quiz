package uz.gita.robo_brain.presentation.ui.sort_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.presentation.ui.sort_math.view_model.SortMathViewModel
import uz.gita.robo_brain.repository.models.SortedMath

class SortMathViewModelImpl : SortMathViewModel,ViewModel() {

    override val questionLiveData: MutableLiveData<List<SortedMath>> = MutableLiveData()

    override val checkLiveData:MutableLiveData<Unit> = MutableLiveData()

    override val gameOverLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val questionCountLiveData: MutableLiveData<Int> = MutableLiveData()

    override fun check(list: List<SortedMath>) {

    }

    override fun nextQuestion() {

    }
}