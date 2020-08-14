package com.kotlintesttask.di.base

import com.kotlintesttask.MainApplication
import com.kotlintesttask.data.repository.MainRepository
import com.kotlintesttask.di.injector.DaggerRepositoryInjector
import com.kotlintesttask.di.injector.RepositoryInjector
import com.kotlintesttask.di.modules.DatabaseModule
import com.kotlintesttask.di.modules.NetworkModule


abstract class BaseRepository {

    private val injector: RepositoryInjector = DaggerRepositoryInjector.builder()
        .networkModule(NetworkModule)
        .databaseModule(            DatabaseModule(
                MainApplication.instance
            )
        )
        .build()

    init {
        inject()
    }

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is MainRepository -> injector.inject(this)
        }
    }
}