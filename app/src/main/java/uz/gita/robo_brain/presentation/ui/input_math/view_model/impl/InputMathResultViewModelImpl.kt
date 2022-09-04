package uz.gita.robo_brain.presentation.ui.input_math.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.presentation.ui.input_math.view_model.InputMathResultViewModel
import uz.gita.robo_brain.repository.input_math.impl.InputMathRepositoryImpl

class InputMathResultViewModelImpl : InputMathResultViewModel,ViewModel() {

    private val repository = InputMathRepositoryImpl.getInstance()

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