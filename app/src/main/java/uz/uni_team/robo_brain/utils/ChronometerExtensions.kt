package uz.uni_team.robo_brain.utils

import android.widget.Chronometer

// Created by Jamshid Isoqov an 8/31/2022

fun Chronometer.getTime(): Int {
    val text = this.text.toString()
    return text.substring(0, 2).toInt() * 60 + text.substring(3).toInt()
}