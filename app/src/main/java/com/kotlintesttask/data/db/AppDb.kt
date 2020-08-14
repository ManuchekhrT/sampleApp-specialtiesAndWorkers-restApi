package com.kotlintesttask.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.kotlintesttask.networking.model.WorkerModel

@Database(entities = [WorkerModel::class], version = 1, exportSchema = false)
@TypeConverters(DataConverter::class)
abstract class AppDb : RoomDatabase() {
    abstract fun workerDao(): WorkerDao
}