package uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.impl

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber
import uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model.Puzzle2048ViewModel
import uz.gita.robo_brain.repository.models.Movement
import uz.gita.robo_brain.repository.puzzle2048.impl.Puzzle2048RepositoryImpl

class Puzzle2048ViewModelImpl : Puzzle2048ViewModel, ViewModel() {

    private val repo = Puzzle2048RepositoryImpl.getInstance()

    override val bestScore: MutableLiveData<Int> = MutableLiveData()

    private val emptyMatrix = getEmptyArray()

    override val currentScore: MutableLiveData<Int> = MutableLiveData(0)

    override val changePosition: MutableLiveData<Pair<Int, Int>> = MutableLiveData()

    override val gameOver: MutableLiveData<Unit> = MutableLiveData()

    override val currentMatrix: MutableLiveData<Array<Array<Int>>> =
        MutableLiveData(emptyMatrix)

    private val minNumber = 2

    init {
        val matrix = repo.getMatrix()
        if (isFirst(matrix)) {
            currentMatrix.postValue(emptyMatrix)
            addElement()
            addElement()
        } else {
            currentMatrix.postValue(matrix)
        }
        bestScore.postValue(repo.getBestScore())
    }

    override fun move(movement: Movement) {
        currentMatrix.postValue(
            when (movement) {
                Movement.LEFT -> moveLeft()
                Movement.RIGHT -> moveRight()
                Movement.UP -> moveUp()
                Movement.DOWN -> moveDown()
            }
        )
        addElement()
    }


    override fun refresh() {
        currentMatrix.value = getEmptyArray()
        addElement()
        addElement()
        currentScore.postValue(0)
        repo.setMatrix(emptyMatrix)
    }

    override fun addScore(pair: Pair<Int, Int>) {
        val matrix = currentMatrix.value ?: emptyMatrix
        val current = (currentScore.value ?: 0) + matrix[pair.first][pair.second]
        currentScore.postValue(current)
        val best = bestScore.value ?: 0
        if (best < current) {
            bestScore.postValue(current)
        }
    }

    override fun quitGame() {
        repo.setBestScore(bestScore.value ?: 0)
        repo.setMatrix(currentMatrix.value ?: emptyMatrix)
    }

    private fun getEmptyArray() = Array(4) { Array(4) { 0 } }


    private fun moveLeft(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!
        for (i in matrix.indices) {
            val list = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].indices) {
                val num = matrix[i][j]
                if (num == 0) continue
                else if (list.isEmpty()) list.add(num)
                else if (list.last() == num && bool) {
                    list[list.lastIndex] = num * 2
                    bool = false
                    changePosition.postValue(Pair(i, list.lastIndex))
                } else {
                    list.add(num)
                    bool = true
                }
                matrix[i][j] = 0
            }
            for (j in list.indices) {
                matrix[i][j] = list[j]
            }
        }
        return matrix
    }

    private fun moveRight(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!
        for (i in matrix.indices) {
            val list = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].lastIndex downTo 0) {
                val num = matrix[i][j]
                if (num == 0) continue
                else if (list.isEmpty()) list.add(num)
                else if (list.last() == num && bool) {
                    list[list.lastIndex] = num * 2
                    bool = false
                    changePosition.postValue(Pair(i, 3 - list.lastIndex))
                } else {
                    list.add(num)
                    bool = true
                }
                matrix[i][j] = 0
            }
            for (j in list.indices) {
                matrix[i][matrix.size - j - 1] = list[j]
            }
        }

        return matrix
    }

    private fun moveUp(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!

        for (i in matrix.indices) {
            val list = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].indices) {
                val num = matrix[j][i]
                if (num == 0) continue
                else if (list.isEmpty()) list.add(num)
                else if (list.last() == num && bool) {
                    list[list.lastIndex] = num * 2
                    bool = false
                    changePosition.postValue(Pair(list.lastIndex, i))
                } else {
                    list.add(num)
                    bool = true
                }
                matrix[j][i] = 0
            }
            for (j in list.indices) {
                matrix[j][i] = list[j]
            }
        }

        return matrix
    }

    private fun moveDown(): Array<Array<Int>> {
        val matrix = currentMatrix.value!!
        for (i in matrix.indices) {
            val list = ArrayList<Int>()
            var bool = true
            for (j in matrix[i].lastIndex downTo 0) {
                val num = matrix[j][i]
                if (num == 0) continue
                else if (list.isEmpty()) list.add(num)
                else if (list.last() == num && bool) {
                    list[list.lastIndex] = num * 2
                    bool = false
                    changePosition.postValue(Pair(3 - list.lastIndex, i))
                } else {
                    list.add(num)
                    bool = true
                }
                matrix[j][i] = 0
            }
            for (j in list.indices) {
                matrix[matrix[i].size - 1 - j][i] = list[j]
            }
        }
        return matrix
    }

    private fun addElement() {
        val matrix = currentMatrix.value ?: getEmptyArray()
        val emptySpaces = findEmptyIndex()
        if (emptySpaces.size > 0) {
            val randomPosition = (Math.random() * emptySpaces.size).toInt()
            val position = emptySpaces[randomPosition]
            matrix[position / matrix.size][position % matrix.size] = minNumber
            currentMatrix.postValue(matrix)
        }
        if (!isGameOver()) {
            gameOver.postValue(Unit)
            repo.setMatrix(emptyMatrix)
        }
    }

    private fun findEmptyIndex(): ArrayList<Int> {
        val matrix = currentMatrix.value ?: getEmptyArray()
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
        val matrix = currentMatrix.value ?: emptyMatrix
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

    private fun isFirst(matrix: Array<Array<Int>>): Boolean {
        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] != 0) return false
            }
        }
        return true
    }

}