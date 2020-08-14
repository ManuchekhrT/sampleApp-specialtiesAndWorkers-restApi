package com.kotlintesttask.di.modules

import android.app.Application
import androidx.room.Room
import com.kotlintesttask.data.db.AppDb
import com.kotlintesttask.data.db.WorkerDao
import com.kotlintesttask.extensions.TEST_TASK_NAME
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(mApplication: Application) {

    private val appDb: AppDb = Room.databaseBuilder(
        mApplication,
        AppDb::class.java,
        TEST_TASK_NAME
    ).fallbackToDestructiveMigration().allowMainThreadQueries().build()


    @Singleton
    @Provides
    internal fun providesRoomDatabase(): AppDb {
        return appDb
    }

    @Singleton
    @Provides
    internal fun providesConfigDao(appDb: AppDb): WorkerDao {
        return appDb.workerDao()
    }

}