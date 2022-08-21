package uz.gita.robo_brain.data.pref

// Created by Jamshid Isoqov an 8/19/2022
interface SharedPref {

    fun getBestScorePuzzle2048(): Int

    fun setBestScorePuzzle2048(bestScore: Int)

    fun getMatrixPuzzle2048(): Array<Array<Int>>

    fun setMatrixPuzzle2048(matrix:Array<Array<Int>>)

}