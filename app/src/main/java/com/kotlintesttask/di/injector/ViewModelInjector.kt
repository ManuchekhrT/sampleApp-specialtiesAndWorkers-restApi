package com.kotlintesttask.di.injector

import com.kotlintesttask.di.modules.RepositoryModule
import com.kotlintesttask.ui.specialty.SpecialtyViewModel
import com.kotlintesttask.ui.worker.WorkerViewModel
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(RepositoryModule::class)])
interface ViewModelInjector {

    fun inject(specialtyViewModel: SpecialtyViewModel)
    fun inject(workerViewModel: WorkerViewModel)

    @Component.Builder
    interface Builder {
        fun build(): ViewModelInjector

        fun repositoryModule(repositoryModule: RepositoryModule): Builder
    }
}