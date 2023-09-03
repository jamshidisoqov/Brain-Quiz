package uz.uni_team.robo_brain.presentation.ui.sort_math.screens.result.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.uni_team.robo_brain.presentation.ui.sort_math.screens.result.SortedMathResultViewModel
import uz.uni_team.robo_brain.repository.sorted_math.impl.SortedMathRepositoryImpl

class SortedMathResultViewModelImpl : SortedMathResultViewModel,ViewModel() {

    private val repository = SortedMathRepositoryImpl.getInstance()

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