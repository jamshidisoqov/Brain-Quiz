package uz.gita.robo_brain.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

// Created by Jamshid Isoqov an 8/30/2022
@Entity(tableName = "puzzle15")
data class StatisticsByPuzzle15(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val moved: Int,
    val time: Int
)
