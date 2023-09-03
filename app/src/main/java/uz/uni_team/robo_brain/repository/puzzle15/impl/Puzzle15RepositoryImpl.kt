package uz.uni_team.robo_brain.repository.puzzle15.impl

import uz.uni_team.robo_brain.data.models.StatisticsByPuzzle15
import uz.uni_team.robo_brain.data.pref.impl.SharedPrefImpl
import uz.uni_team.robo_brain.repository.puzzle15.Puzzle15Repository

class Puzzle15RepositoryImpl private constructor() : Puzzle15Repository {

    private val sharedPref = SharedPrefImpl.getInstance()


    override fun getNumberList(): List<Int> = sharedPref.getNumberListPuzzle15()

    override fun getMove(): Int = sharedPref.getMovedPuzzle15()

    override fun getTime(): Int = sharedPref.getTimePuzzle15()

    override fun setNumberList(list: List<Int>) {
        sharedPref.setNumberListPuzzle15(list)
    }

    override fun setMove(move: Int) {
        sharedPref.setMovedPuzzle15(move)
    }

    override fun setTime(time: Int) {
        sharedPref.setTimePuzzle15(time)
    }

    override fun setNewGame(newGame: Boolean) = sharedPref.setNewGame(newGame)

    override fun getNewGame(): Boolean = sharedPref.getNewGame()

    override fun getBestResult(): StatisticsByPuzzle15 = sharedPref.getBestResult()

    override fun setBestResult(statisticsByPuzzle15: StatisticsByPuzzle15) =
        sharedPref.setBestResult(statisticsByPuzzle15)

    override fun getMusic(): Boolean = sharedPref.getMusic()


    companion object {
        private lateinit var instance: Puzzle15Repository

        fun getInstance(): Puzzle15Repository {
            if (!Companion::instance.isInitialized) {
                instance = Puzzle15RepositoryImpl()
            }
            return instance
        }
    }
}