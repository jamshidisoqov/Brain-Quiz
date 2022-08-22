package uz.gita.robo_brain.utils

import uz.gita.robo_brain.repository.models.MathQuizData

// Created by Jamshid Isoqov an 8/22/2022
object GenerateMathQuiz {
    fun generate(level: Int):MathQuizData{
        return MathQuizData("1-3*4+5",-6)
    }
}