package com.kotlintesttask.di.base

import androidx.lifecycle.ViewModel
import com.kotlintesttask.di.injector.DaggerViewModelInjector
import com.kotlintesttask.di.injector.ViewModelInjector
import com.kotlintesttask.di.modules.RepositoryModule
import com.kotlintesttask.ui.specialty.SpecialtyViewModel
import com.kotlintesttask.ui.worker.WorkerViewModel

abstract class BaseViewModel : ViewModel() {

    private val injector: ViewModelInjector = DaggerViewModelInjector.builder()
        .repositoryModule(RepositoryModule)
        .build()

    init {
        inject()
    }


    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is SpecialtyViewModel -> injector.inject(this)
            is WorkerViewModel -> injector.inject(this)
        }
    }

}