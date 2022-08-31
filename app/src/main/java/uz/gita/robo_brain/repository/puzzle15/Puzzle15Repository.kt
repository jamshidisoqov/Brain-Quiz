package uz.gita.robo_brain.repository.puzzle15

// Created by Jamshid Isoqov an 8/30/2022
interface Puzzle15Repository {

    fun getNumberList():List<Int>

    fun getMove():Int

    fun getTime():Int

    fun setNumberList(list: List<Int>)

    fun setMove(move:Int)

    fun setTime(time:Int)

}