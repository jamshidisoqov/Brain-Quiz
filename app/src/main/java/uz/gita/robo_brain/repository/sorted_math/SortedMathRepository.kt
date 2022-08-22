package uz.gita.robo_brain.repository.sorted_math

import uz.gita.robo_brain.repository.models.SortedMath

// Created by Jamshid Isoqov an 8/22/2022
interface SortedMathRepository {

    fun getQuestions(question: Int):List<SortedMath>

}