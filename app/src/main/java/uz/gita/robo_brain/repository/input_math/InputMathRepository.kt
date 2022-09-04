package uz.gita.robo_brain.repository.input_math

import uz.gita.robo_brain.repository.models.InputMathData

// Created by Jamshid Isoqov an 9/4/2022
interface InputMathRepository {

    fun getQuestion(level: Int): InputMathData

    fun setBestResult(result: Int)

    fun getBestResult(): Int

}