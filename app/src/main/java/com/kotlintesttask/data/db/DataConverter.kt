package com.kotlintesttask.data.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kotlintesttask.networking.model.SpecialtyModel

class DataConverter {

    @TypeConverter
    fun fromSpecialtyList(value: List<SpecialtyModel>): String {
        val aGson = Gson()
        val type = object : TypeToken<List<SpecialtyModel>>() {}.type
        return aGson.toJson(value, type)
    }

    @TypeConverter
    fun toSpecialtyList(value: String): List<SpecialtyModel> {
        val aGson = Gson()
        val type = object : TypeToken<List<SpecialtyModel>>() {}.type
        return aGson.fromJson(value, type)
    }
}