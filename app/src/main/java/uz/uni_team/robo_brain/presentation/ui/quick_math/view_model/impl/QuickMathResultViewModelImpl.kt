package uz.uni_team.robo_brain.presentation.ui.quick_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.uni_team.robo_brain.presentation.ui.quick_math.view_model.QuickMathResultViewModel
import uz.uni_team.robo_brain.repository.quick_math.impl.QuickMathRepositoryImpl

class QuickMathResultViewModelImpl : QuickMathResultViewModel, ViewModel() {

    private val repository = QuickMathRepositoryImpl.getInstance()

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val bestResultLiveData: MutableLiveData<Int> = MutableLiveData()

    override val refreshLiveData: MutableLiveData<Unit> = MutableLiveData()

    init {
        bestResultLiveData.value = repository.getBestResult()
    }

    override fun refreshClick() {
        refreshLiveData.value = Unit
    }

    override fun backClick() {
        backLiveData.value = Unit
    }
}