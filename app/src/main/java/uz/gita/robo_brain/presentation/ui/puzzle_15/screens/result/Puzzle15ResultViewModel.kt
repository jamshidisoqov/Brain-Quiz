package uz.gita.robo_brain.presentation.ui.puzzle_15.screens.result

import androidx.lifecycle.LiveData
import uz.gita.robo_brain.data.models.StatisticsByPuzzle15

interface Puzzle15ResultViewModel {

    val bestResult: LiveData<StatisticsByPuzzle15>

    val backLiveData: LiveData<Unit>

    val refreshLiveData: LiveData<Unit>

    val statisticsLiveData: LiveData<Unit>

    fun statisticsClicked()

    fun refreshClicked()

    fun backClicked()

}