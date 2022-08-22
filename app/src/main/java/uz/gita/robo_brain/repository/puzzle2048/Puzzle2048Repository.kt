package uz.gita.robo_brain.repository.puzzle2048

// Created by Jamshid Isoqov an 8/19/2022
interface Puzzle2048Repository {

    fun getMatrix(): Array<Array<Int>>

    fun setMatrix(matrix: Array<Array<Int>>)

    fun getBestScore(): Int

    fun setBestScore(score: Int)

    fun setCurrentScore(currentScore:Int)

    fun getCurrentScore():Int
}