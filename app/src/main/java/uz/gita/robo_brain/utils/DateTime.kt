package uz.gita.robo_brain.utils
// Created by Jamshid Isoqov an 9/2/2022


fun Int.getTimeFormat(): String {
    val min = this / 60
    val sec = this % 60
    return "${getFormat(min)}:${getFormat(sec)}"
}

private fun getFormat(time: Int): String {
    return if (time < 10) {
        "0$time"
    } else {
        "$time"
    }
}