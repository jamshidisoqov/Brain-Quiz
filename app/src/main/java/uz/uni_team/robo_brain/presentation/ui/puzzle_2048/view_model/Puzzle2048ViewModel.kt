package uz.uni_team.robo_brain.presentation.ui.puzzle_2048.view_model

import androidx.lifecycle.LiveData
import uz.uni_team.robo_brain.repository.models.Movement

interface Puzzle2048ViewModel {

    val bestScore: LiveData<Int>

    val currentScore: LiveData<Int>

    val changePosition:LiveData<Pair<Int,Int>>

    val gameOver: LiveData<Unit>

    val winnerLiveData:LiveData<Unit>

    val currentMatrix: LiveData<Array<Array<Int>>>

    val musicLiveData:LiveData<Boolean>

    fun move(movement: Movement)

    fun refresh()

    fun addScore(pair: Pair<Int,Int>)

    fun quitGame()

}