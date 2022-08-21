package uz.gita.robo_brain.utils

import uz.gita.robo_brain.R

// Created by Jamshid Isoqov an 8/21/2022
object Constants {

    private val backgroundList = listOf(
        R.drawable.default_background,
        R.drawable.default_background,
        R.drawable.bg_4,
        R.drawable.bg_8,
        R.drawable.bg_16,
        R.drawable.bg_32,
        R.drawable.bg_64,
        R.drawable.bg_128,
        R.drawable.bg_256,
        R.drawable.bg_512,
        R.drawable.bg_1024,
        R.drawable.bg_2048
    )

    fun getBackground(number: Int): Int = backgroundList[number.degreeOfTwo()]

}

fun Int.degreeOfTwo(): Int {
    var value = this
    var deg = 0
    while (value > 1) {
        value /= 2
        deg++
    }
    return deg
}