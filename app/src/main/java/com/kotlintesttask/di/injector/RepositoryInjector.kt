package com.kotlintesttask.di.injector

import com.kotlintesttask.data.repository.MainRepository
import com.kotlintesttask.di.modules.DatabaseModule
import com.kotlintesttask.di.modules.NetworkModule
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(NetworkModule::class), (DatabaseModule::class)])
interface RepositoryInjector {

    fun inject(mainRepository: MainRepository)

    @Component.Builder
    interface Builder {
        fun build(): RepositoryInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun databaseModule(databaseModule: DatabaseModule): Builder
    }
}