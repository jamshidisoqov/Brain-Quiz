package uz.gita.robo_brain.presentation.ui.true_false.screens.result.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.presentation.ui.true_false.screens.result.TrueFalseResultViewModel
import uz.gita.robo_brain.repository.true_false.impl.TrueFalseRepositoryImpl

class TrueFalseResultViewModelImpl : TrueFalseResultViewModel,ViewModel() {

    private val repository = TrueFalseRepositoryImpl.getInstance()

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