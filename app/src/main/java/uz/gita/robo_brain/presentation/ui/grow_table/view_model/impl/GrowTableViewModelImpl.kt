package uz.gita.robo_brain.presentation.ui.grow_table.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.gita.robo_brain.presentation.ui.grow_table.view_model.GrowTableViewModel
import uz.gita.robo_brain.repository.table_grow.impl.TableOfGrowRepositoryImpl

class GrowTableViewModelImpl : GrowTableViewModel, ViewModel() {

    private val repository = TableOfGrowRepositoryImpl.getInstance()

    override val bestResultLiveData: MutableLiveData<Int> = MutableLiveData()

    override val timerLiveData: MutableLiveData<Int> = MutableLiveData()

    override val gameOverLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val numberListLiveData: MutableLiveData<List<Int>> = MutableLiveData()

    override val numberLiveData: MutableLiveData<Int> = MutableLiveData()

    override val finishLiveData: MutableLiveData<Int> = MutableLiveData()

    private var time = 0

    init {
        newGame()
    }

    override fun check(number: Int) {
        if (number != numberLiveData.value) {
            gameOverLiveData.value = Unit
            time = -1
        }
        val num = repository.getNumber()
        if (num == -1) {
            finishLiveData.value = time
            repository.setBestResult(time)
            time = -1
        } else
            numberLiveData.value = num
    }

    override fun newGame() {
        time = 0
        viewModelScope.launch {
            while (time >= 0) {
                timerLiveData.value = time
                time++
                delay(1000)
            }
        }
        numberListLiveData.value = repository.getNumberList()
        numberLiveData.value = repository.getNumber()
        bestResultLiveData.value = repository.getBestResult()

    }

    override fun back() {
        backLiveData.value = Unit
    }
}