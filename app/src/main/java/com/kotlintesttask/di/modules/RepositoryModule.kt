package com.kotlintesttask.di.modules

import com.kotlintesttask.data.repository.MainRepository
import dagger.Module
import dagger.Provides

@Module
// Safe here as we are dealing with a Dagger 2 module
@Suppress("unused")
object RepositoryModule {

    @Provides
    internal fun provideMainRepository(): MainRepository {
        return MainRepository()
    }

}