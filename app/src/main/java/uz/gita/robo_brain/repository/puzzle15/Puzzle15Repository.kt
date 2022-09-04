package uz.gita.robo_brain.repository.puzzle15

import uz.gita.robo_brain.data.models.StatisticsByPuzzle15

// Created by Jamshid Isoqov an 8/30/2022
interface Puzzle15Repository {

    fun getNumberList(): List<Int>

    fun getMove(): Int

    fun getTime(): Int

    fun setNumberList(list: List<Int>)

    fun setMove(move: Int)

    fun setTime(time: Int)

    fun setNewGame(newGame: Boolean)

    fun getNewGame(): Boolean

    fun getBestResult(): StatisticsByPuzzle15

    fun setBestResult(statisticsByPuzzle15: StatisticsByPuzzle15)

    fun getMusic():Boolean

}