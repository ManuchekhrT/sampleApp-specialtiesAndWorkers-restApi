package com.kotlintesttask.ui.specialty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlintesttask.data.repository.MainRepository
import com.kotlintesttask.di.base.BaseViewModel
import com.kotlintesttask.networking.model.ResponseModel
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject

class SpecialtyViewModel : BaseViewModel() {

    @Inject
    lateinit var mainRepository: MainRepository

    fun getResponse(): LiveData<ResponseModel> =
        mainRepository.getResponse()


}