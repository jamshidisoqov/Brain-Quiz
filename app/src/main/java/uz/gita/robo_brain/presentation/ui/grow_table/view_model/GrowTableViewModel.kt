package uz.gita.robo_brain.presentation.ui.grow_table.view_model

import androidx.lifecycle.LiveData

interface GrowTableViewModel {

    val bestResultLiveData: LiveData<Int>

    val timerLiveData: LiveData<Int>

    val gameOverLiveData:LiveData<Unit>

    val backLiveData:LiveData<Unit>

    val numberListLiveData: LiveData<List<Int>>

    val numberLiveData: LiveData<Int>

    val finishLiveData: LiveData<Int>

    val musicLiveData:LiveData<Boolean>

    fun check(number: Int)

    fun newGame()

    fun back()

}