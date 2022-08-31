package uz.gita.robo_brain.presentation.ui.puzzle_15.view_model

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.repository.models.Coordinate

interface NumberPuzzleViewModel {

    val numbers: LiveData<List<Int>>

    val moves: LiveData<Int>

    val backLiveData: LiveData<Unit>

    val time: LiveData<Int>

    val emptyCoordinate: LiveData<Coordinate>

    val modifiedCoordinate: LiveData<Pair<Coordinate, Coordinate>>

    fun move(x: Int, y: Int)

    fun newGame()

    fun back()

    fun saveData(numbersList: List<Int>,time:Int)

}