package uz.uni_team.robo_brain.presentation.ui.puzzle_15.view_model

import androidx.lifecycle.LiveData
import uz.uni_team.robo_brain.data.models.StatisticsByPuzzle15
import uz.uni_team.robo_brain.repository.models.Coordinate

interface NumberPuzzleViewModel {

    val numbers: LiveData<List<Int>>

    val moves: LiveData<Int>

    val backLiveData: LiveData<Unit>

    val time: LiveData<Int>

    val emptyCoordinate: LiveData<Coordinate>

    val modifiedCoordinate: LiveData<Pair<Coordinate, Coordinate>>

    val checkLiveData: LiveData<StatisticsByPuzzle15>

    val openResultLiveData: LiveData<StatisticsByPuzzle15>

    val musicLiveData:LiveData<Boolean>

    fun move(x: Int, y: Int)

    fun newGame()

    fun back()

    fun saveData(numbersList: List<Int>, time: Int)

    fun check(numbersList: List<Int>,time: Int)

}