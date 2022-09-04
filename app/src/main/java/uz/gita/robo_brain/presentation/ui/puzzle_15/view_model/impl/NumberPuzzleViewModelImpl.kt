package uz.gita.robo_brain.presentation.ui.puzzle_15.view_model.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import uz.gita.robo_brain.data.models.StatisticsByPuzzle15
import uz.gita.robo_brain.presentation.ui.puzzle_15.view_model.NumberPuzzleViewModel
import uz.gita.robo_brain.repository.models.Coordinate
import uz.gita.robo_brain.repository.puzzle15.impl.Puzzle15RepositoryImpl
import java.lang.Math.abs
import kotlin.math.sqrt

class NumberPuzzleViewModelImpl : NumberPuzzleViewModel, ViewModel() {

    private val repository = Puzzle15RepositoryImpl.getInstance()

    override val moves: MutableLiveData<Int> = MutableLiveData(0)

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val time: MutableLiveData<Int> = MutableLiveData()

    override val emptyCoordinate: MutableLiveData<Coordinate> = MutableLiveData()

    override val modifiedCoordinate: MutableLiveData<Pair<Coordinate, Coordinate>> =
        MutableLiveData()

    override val checkLiveData: MutableLiveData<StatisticsByPuzzle15> = MutableLiveData()

    override val openResultLiveData: MutableLiveData<StatisticsByPuzzle15> = MutableLiveData()

    override val musicLiveData: MutableLiveData<Boolean> = MutableLiveData(repository.getMusic())

    override val numbers: MutableLiveData<List<Int>> = MutableLiveData()

    init {
        if (repository.getNewGame()) {
            generateNumber()
            time.value = 0
        } else {
            val list = repository.getNumberList()
            val empty = list.indexOf(0)
            time.value = repository.getTime()
            emptyCoordinate.value = Coordinate(empty % 4, empty / 4)
            numbers.value = list
        }
        moves.value = repository.getMove()
        repository.setNewGame(false)
    }

    override fun move(x: Int, y: Int) {
        val empty = emptyCoordinate.value ?: Coordinate(3, 3)
        val eX: Int = empty.x
        val eY: Int = empty.y
        val dX: Int = abs(x - eX)
        val dY: Int = abs(y - eY)
        if (dX + dY == 1) {
            val coordinate = Coordinate(x, y)
            modifiedCoordinate.value = Pair(empty, coordinate)
            emptyCoordinate.value = coordinate
            moves.value = moves.value!! + 1
        }
    }

    override fun newGame() {
        generateNumber()
        moves.value = 0
        time.value = 0
    }

    override fun back() {
        backLiveData.value = Unit
    }

    override fun saveData(numbersList: List<Int>, time: Int) {
        repository.setTime(time)
        repository.setMove(moves.value ?: 0)
        repository.setNumberList(numbersList)
    }

    override fun check(numbersList: List<Int>, time: Int) {
        for (i in 1..15) {
            if (numbersList[i - 1] != i) return
        }
        val data = StatisticsByPuzzle15(moves.value ?: 0, time)
        viewModelScope.launch {
            repository.setBestResult(statisticsByPuzzle15 = data)
            repository.setTime(0)
            repository.setMove(0)
            openResultLiveData.value = data
        }
        repository.setNewGame(true)
    }

    private fun generateNumber() {
        val numberList = ArrayList<Int>()
        for (i in 1..15) {
            numberList.add(i)
        }
        numberList.shuffle()
        numberList.add(numberList.size, 0)
        if (isSolvable(numberList.toIntArray())) {
            emptyCoordinate.value = Coordinate(3, 3)
            numbers.value = numberList
        } else generateNumber()
    }

    private fun isSolvable(puzzle: IntArray): Boolean {
        var parity = 0
        val gridWidth = sqrt(puzzle.size.toDouble()).toInt()
        var row = 0
        var blankRow = 0
        for (i in puzzle.indices) {
            if (i % gridWidth == 0) {
                row++
            }
            if (puzzle[i] == 0) {
                blankRow = row
                continue
            }
            for (j in i + 1 until puzzle.size) {
                if (puzzle[i] > puzzle[i] && puzzle[i] != 0) {
                    parity++
                }
            }
        }
        return if (gridWidth % 2 == 0) {
            if (blankRow % 2 == 0) {
                parity % 2 == 0
            } else {
                parity % 2 != 0
            }
        } else {
            parity % 2 == 0
        }
    }
}