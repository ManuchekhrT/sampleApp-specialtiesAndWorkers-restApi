package com.kotlintesttask.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kotlintesttask.data.db.WorkerDao
import com.kotlintesttask.di.base.BaseRepository
import com.kotlintesttask.networking.api.MainApi
import com.kotlintesttask.networking.model.ResponseModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MainRepository : BaseRepository() {

    @Inject
    lateinit var api: MainApi

    @Inject
    lateinit var workerDao: WorkerDao

    fun getResponse(): LiveData<ResponseModel> {
        val specialtiesLiveData = MutableLiveData<ResponseModel>()

        GlobalScope.launch {
            //check whether local db is empty and then request for data
            workerDao.getWorkers().let {
                if (it.isNullOrEmpty()) {
                    val response = api.getResponseAsync().await()
                    response.body()?.let { resp ->
                        workerDao.insertWorkers(resp.response)
                    }
                    specialtiesLiveData.postValue(response.body())
                } else {
                    specialtiesLiveData.postValue(ResponseModel(it))
                }
            }
        }

        return specialtiesLiveData
    }

}