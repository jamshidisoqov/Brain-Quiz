package uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.impl

import androidx.lifecycle.MutableLiveData
import uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.Puzzle2048ViewModel
import uz.gita.robo_brain.repository.models.Movement

class Puzzle2048ViewModelImpl : Puzzle2048ViewModel {

    init {
        addElement()
        addElement()
    }

    override val bestScore: MutableLiveData<Int> = MutableLiveData()

    override val currentScore: MutableLiveData<Int> = MutableLiveData(0)

    override val gameOver: MutableLiveData<Unit> = MutableLiveData()

    override val currentMatrix: MutableLiveData<Array<Array<Int>>> =
        MutableLiveData(Array(4) { Array(4) { 0 } })

    private val minNumber = 2

    override fun move(movement: Movement) {
        currentMatrix.postValue(
            when (movement) {
                Movement.LEFT -> moveLeft()
                Movement.RIGHT -> moveRight()
                Movement.UP -> moveUp()
                Movement.DOWN -> moveDown()
            }
        )
    }


    override fun refresh() {

    }

    override fun quitGame() {

    }


    private fun moveLeft(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!

        return matrix
    }

    private fun moveRight(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!

        return matrix
    }

    private fun moveUp(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!

        return matrix
    }

    private fun moveDown(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!

        return matrix
    }

    private fun addElement() {
        val matrix = currentMatrix.value!!
        val emptySpaces = findEmptyIndex()
        if (emptySpaces.size > 0) {
            val randomPosition = (Math.random() * emptySpaces.size).toInt()
            val position = emptySpaces[randomPosition]
            matrix[position / matrix.size][position % matrix.size] = minNumber
        } else gameOver.postValue(Unit)
        if (isGameOver()) gameOver.postValue(Unit)
    }

    private fun findEmptyIndex(): ArrayList<Int> {
        val matrix = currentMatrix.value!!
        val list = ArrayList<Int>()
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0)
                    list.add(matrix.size * i + j)
            }
        }
        return list
    }

    private fun isGameOver(): Boolean {
        val matrix = currentMatrix.value!!
        for (i in matrix.indices) {
            for (j in 0..3) {
                if (matrix[i][j] == 0) return true
            }
        }

        for (i in matrix.indices) {
            for (j in 0..2) {
                if (matrix[i][j] == matrix[i][j + 1]) return true
            }
        }

        for (i in 0..3) {
            for (j in 0..2) {
                if (matrix[j][i] == matrix[j + 1][i]) return true
            }
        }
        return false
    }

}