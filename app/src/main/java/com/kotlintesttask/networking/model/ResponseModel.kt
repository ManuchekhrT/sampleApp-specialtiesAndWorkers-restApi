package com.kotlintesttask.networking.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(
    @SerializedName("response")
    var response: List<WorkerModel>
)
