package com.kotlintesttask.data.db

import androidx.room.*
import com.kotlintesttask.networking.model.WorkerModel

@Dao
interface WorkerDao {

    @Query("SELECT * FROM worker")
    fun getWorkers(): List<WorkerModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWorkers(workers: List<WorkerModel>)

}