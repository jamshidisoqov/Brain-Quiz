package uz.gita.robo_brain.presentation.ui.puzzle_15.view_model.impl

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.robo_brain.presentation.ui.puzzle_15.view_model.NumberPuzzleViewModel
import uz.gita.robo_brain.repository.models.Coordinate
import uz.gita.robo_brain.repository.puzzle15.impl.Puzzle15RepositoryImpl
import java.lang.Math.abs

class NumberPuzzleViewModelImpl : NumberPuzzleViewModel, ViewModel() {

    private val repository = Puzzle15RepositoryImpl.getInstance()

    override val moves: MutableLiveData<Int> = MutableLiveData(0)

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val time: MutableLiveData<Int> = MutableLiveData()

    override val emptyCoordinate: MutableLiveData<Coordinate> = MutableLiveData()

    override val modifiedCoordinate: MutableLiveData<Pair<Coordinate, Coordinate>> =
        MutableLiveData()

    override val numbers: MutableLiveData<List<Int>> = MutableLiveData()

    init {
        if (repository.getTime() == 0) {
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

    private fun generateNumber() {
        val numberList = ArrayList<Int>()
        for (i in 1..15) {
            numberList.add(i)
        }
        numberList.shuffle()
        numberList.add(numberList.size, 0)
        emptyCoordinate.value = Coordinate(3, 3)
        numbers.value = numberList
    }
}