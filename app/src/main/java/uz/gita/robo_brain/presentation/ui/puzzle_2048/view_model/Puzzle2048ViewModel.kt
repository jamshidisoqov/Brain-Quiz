package uz.gita.robo_brain.presentation.ui.puzzle_2048.view_model

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.repository.models.Movement

interface Puzzle2048ViewModel {

    val bestScore: LiveData<Int>

    val currentScore: LiveData<Int>

    val changePosition:LiveData<Pair<Int,Int>>

    val gameOver: LiveData<Unit>

    val currentMatrix: LiveData<Array<Array<Int>>>

    fun move(movement: Movement)

    fun refresh()

    fun addScore(score:Int)

    fun quitGame()

}