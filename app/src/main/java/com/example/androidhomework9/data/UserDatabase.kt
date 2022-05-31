package com.example.androidhomework9.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.androidhomework9.api.dto.response.DataItem

@Database(entities = [DataItem::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun dataItemDao(): DataItemDao

    companion object {
        @Volatile
        private var instance: UserDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            "USERS"
        ).build()
    }
}