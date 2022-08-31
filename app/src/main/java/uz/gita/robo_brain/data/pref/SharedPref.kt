package uz.gita.robo_brain.data.pref

// Created by Jamshid Isoqov an 8/19/2022
interface SharedPref {

    //puzzle2048
    fun getBestScorePuzzle2048(): Int

    fun setBestScorePuzzle2048(bestScore: Int)

    fun getMatrixPuzzle2048(): Array<Array<Int>>

    fun setMatrixPuzzle2048(matrix: Array<Array<Int>>)

    fun getCurrentScorePuzzle2048(): Int

    fun setCurrentScorePuzzle2048(currentScore: Int)

    //puzzle 15

    fun getNumberListPuzzle15(): List<Int>

    fun setNumberListPuzzle15(list: List<Int>)

    fun getMovedPuzzle15(): Int

    fun setMovedPuzzle15(moved: Int)

    fun getTimePuzzle15(): Int

    fun setTimePuzzle15(time: Int)


}