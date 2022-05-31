package com.example.androidhomework9.api.dto.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "USERS")
data class DataItem(
    @ColumnInfo(name = "ID")
    val id: Int,
    @ColumnInfo(name = "EMAIL")
    val email: String,
    @ColumnInfo(name = "FIRST_NAME")
    @SerializedName("first_name") val firstName: String,
    @ColumnInfo(name = "LAST_NAME")
    @SerializedName("last_name") val lastName: String,
    @ColumnInfo(name = "AVATAR")
    val avatar: String
) {
    @PrimaryKey(autoGenerate = true)
    var uid = 0
}