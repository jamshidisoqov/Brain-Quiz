package uz.gita.robo_brain.data.room.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update

// Created by Jamshid Isoqov an 8/19/2022
interface BaseDao<T> {
    @Insert
    fun insert(data: T)

    @Update
    fun update(data: T)

    @Delete
    fun delete(data: T)
}