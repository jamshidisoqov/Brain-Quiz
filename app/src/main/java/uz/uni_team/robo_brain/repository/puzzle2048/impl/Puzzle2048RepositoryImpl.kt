package uz.uni_team.robo_brain.repository.puzzle2048.impl

import uz.uni_team.robo_brain.data.pref.impl.SharedPrefImpl
import uz.uni_team.robo_brain.repository.puzzle2048.Puzzle2048Repository

class Puzzle2048RepositoryImpl private constructor() : Puzzle2048Repository {

    private val sharedPref = SharedPrefImpl.getInstance()

    override fun getMatrix() = sharedPref.getMatrixPuzzle2048()

    override fun setMatrix(matrix: Array<Array<Int>>) {
        sharedPref.setMatrixPuzzle2048(matrix)
    }

    override fun getBestScore(): Int = sharedPref.getBestScorePuzzle2048()

    override fun setBestScore(score: Int) = sharedPref.setBestScorePuzzle2048(score)

    override fun setCurrentScore(currentScore: Int) {
        sharedPref.setCurrentScorePuzzle2048(currentScore)
    }

    override fun getCurrentScore() =
        sharedPref.getCurrentScorePuzzle2048()

    override fun getMusic()  = sharedPref.getMusic()

    companion object {
        private lateinit var instance: Puzzle2048Repository

        fun getInstance(): Puzzle2048Repository {
            if (!Companion::instance.isInitialized) {
                instance = Puzzle2048RepositoryImpl()
            }
            return instance
        }
    }
}