package com.kotlintesttask.networking.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class SpecialtyModel(
    @SerializedName("specialty_id")
    var specialtyId: Int,
    @SerializedName("name")
    var name: String
) : Serializable
