package com.kotlintesttask.ui.worker

import androidx.lifecycle.LiveData
import com.kotlintesttask.data.repository.MainRepository
import com.kotlintesttask.di.base.BaseViewModel
import com.kotlintesttask.networking.model.ResponseModel
import javax.inject.Inject

class WorkerViewModel : BaseViewModel() {
    @Inject
    lateinit var mainRepository: MainRepository

    fun getResponse(): LiveData<ResponseModel> =
        mainRepository.getResponse()
}