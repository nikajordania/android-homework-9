package com.example.androidhomework9.api.dto.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



data class Users(

    @SerializedName("per_page") val perPage: Int,
    val total: Int,
    val data: List<DataItem>,
    val page: Int,
    @SerializedName("total_pages") val totalPages: Int,
    val support: Support
)

data class Support(
    val text: String,
    val url: String
)
