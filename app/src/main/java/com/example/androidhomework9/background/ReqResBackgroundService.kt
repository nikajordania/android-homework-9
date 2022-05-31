package com.example.androidhomework9.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.api.dto.response.Users
import com.example.androidhomework9.data.UserDatabase

class ReqResBackgroundService(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    private var users = mutableListOf<Users>()

    override fun doWork(): Result {
        return try {
            RestClient.reqResService.getUsers(1).execute().body()?.let { users.add(it) }
            RestClient.reqResService.getUsers(2).execute().body()?.let { users.add(it) }
            RestClient.reqResService.getUsers(3).execute().body()?.let { users.add(it) }

            Log.d("USERS", users.toString())
            users.forEach {
                Log.d("USER", it.toString())
                UserDatabase.invoke(applicationContext).distanceDao().insertAll(it.data[0])
            }

            return Result.success()
        } catch (t: Throwable) {
            Log.e("USERS", "Error can't get users")
            Result.failure()
        }
    }
}