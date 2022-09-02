package uz.gita.robo_brain.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

// Created by Jamshid Isoqov an 8/30/2022
@Parcelize
data class StatisticsByPuzzle15(
    val moved: Int,
    val time: Int
) : Parcelable
