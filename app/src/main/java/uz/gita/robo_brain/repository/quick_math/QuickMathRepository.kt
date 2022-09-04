package uz.gita.robo_brain.repository.quick_math

import uz.gita.robo_brain.repository.models.MathData

// Created by Jamshid Isoqov an 9/3/2022
interface QuickMathRepository {

    fun getQuestion(level:Int):MathData

    fun setBestResult(result:Int)

    fun getBestResult():Int

    fun getMusic():Boolean

}