package uz.gita.robo_brain.repository.table_grow

// Created by Jamshid Isoqov an 9/4/2022
interface TableOfGrowRepository {

    fun getNumberList(): List<Int>

    fun getNumber(): Int

    fun setBestResult(result: Int)

    fun getBestResult(): Int

}