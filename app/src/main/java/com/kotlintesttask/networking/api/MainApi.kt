package com.kotlintesttask.networking.api

import com.kotlintesttask.extensions.TEST_TASK_API
import com.kotlintesttask.networking.model.ResponseModel
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface MainApi {

    @GET(TEST_TASK_API)
    fun getResponseAsync(): Deferred<Response<ResponseModel>>

}
