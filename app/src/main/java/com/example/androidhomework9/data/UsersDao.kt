package com.example.androidhomework9.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.androidhomework9.api.dto.response.DataItem
import com.example.androidhomework9.api.dto.response.Users

@Dao
interface UsersDao {
    @Query("SELECT * FROM USERS")
    fun getAll(): List<DataItem>

    @Insert
    fun insertAll(vararg users: DataItem)

    @Delete
    fun deleteUser(users: DataItem)

    @Query("DELETE FROM USERS")
    fun deleteAll()

}