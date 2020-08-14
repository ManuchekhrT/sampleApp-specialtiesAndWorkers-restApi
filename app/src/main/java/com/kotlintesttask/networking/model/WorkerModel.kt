package com.kotlintesttask.networking.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.kotlintesttask.data.db.DataConverter
import java.io.Serializable

@Entity(tableName = "worker")
data class WorkerModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "first_name") @SerializedName("f_name")
    var firstName: String,
    @ColumnInfo(name = "last_name") @SerializedName("l_name")
    var lastName: String,
    @ColumnInfo(name = "birthday") @SerializedName("birthday")
    var birthday: String?,
    @ColumnInfo(name = "avatar_url") @SerializedName("avatr_url")
    var avatarUrl: String?,
    @TypeConverters(DataConverter::class) @SerializedName("specialty")
    var specialty: List<SpecialtyModel>
) : Serializable
