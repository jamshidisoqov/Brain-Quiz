package uz.gita.robo_brain.repository.true_false

import uz.gita.robo_brain.repository.models.TrueFalseData

// Created by Jamshid Isoqov an 9/3/2022
interface TrueFalseRepository {

    fun getQuestion(level:Int): TrueFalseData

    fun setBestResult(result: Int)

    fun getBestResult(): Int

    fun getMusic():Boolean

}