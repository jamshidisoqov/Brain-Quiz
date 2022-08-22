package uz.gita.robo_brain.repository.models

// Created by Jamshid Isoqov an 8/22/2022
data class MathQuizData(
    val question: String,
    val answer: Int
) {
    fun toSortedMath() = SortedMath(question,answer)
}