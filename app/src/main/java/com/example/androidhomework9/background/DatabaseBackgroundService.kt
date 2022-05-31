package com.example.androidhomework9.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.androidhomework9.data.UserDatabase

class DatabaseBackgroundService(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    override fun doWork(): Result {
        return try {
            UserDatabase.invoke(applicationContext).dataItemDao().getAll()
                .filter { it.id > 10 }
                .forEach {
                    UserDatabase.invoke(applicationContext).dataItemDao().deleteUser(it)
                    Log.d("DELETE PROCEDURE", "user $it.id")
                }

            return Result.success()
        } catch (e: RuntimeException) {
            Log.e("DELETE PROCEDURE", "Error can't delete user ${e.message.toString()}")
            Result.failure()
        }
    }
}