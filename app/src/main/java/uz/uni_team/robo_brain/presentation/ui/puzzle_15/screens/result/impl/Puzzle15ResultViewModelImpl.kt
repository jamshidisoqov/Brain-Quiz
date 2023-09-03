package uz.uni_team.robo_brain.presentation.ui.puzzle_15.screens.result.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.uni_team.robo_brain.data.models.StatisticsByPuzzle15
import uz.uni_team.robo_brain.presentation.ui.puzzle_15.screens.result.Puzzle15ResultViewModel
import uz.uni_team.robo_brain.repository.puzzle15.impl.Puzzle15RepositoryImpl

class Puzzle15ResultViewModelImpl : Puzzle15ResultViewModel, ViewModel() {

    private val repository = Puzzle15RepositoryImpl.getInstance()

    override val bestResult: MutableLiveData<StatisticsByPuzzle15> = MutableLiveData()

    override val backLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val refreshLiveData: MutableLiveData<Unit> = MutableLiveData()

    override val statisticsLiveData: MutableLiveData<Unit> = MutableLiveData()

    init {
        bestResult.value = repository.getBestResult()
    }

    override fun statisticsClicked() {
        statisticsLiveData.value = Unit
    }

    override fun refreshClicked() {
        refreshLiveData.value = Unit
    }

    override fun backClicked() {
        backLiveData.value = Unit
    }
}