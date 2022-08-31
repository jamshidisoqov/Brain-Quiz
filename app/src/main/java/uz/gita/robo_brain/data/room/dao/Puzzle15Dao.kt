package uz.gita.robo_brain.data.room.dao

import androidx.lifecycle.LiveData
import androidx.room.Query
import uz.gita.robo_brain.data.room.entity.StatisticsByPuzzle15

// Created by Jamshid Isoqov an 8/30/2022
interface Puzzle15Dao : BaseDao<StatisticsByPuzzle15> {

    @Query("SELECT*FROM puzzle15")
    fun getAllStatistics(): LiveData<List<StatisticsByPuzzle15>>

}