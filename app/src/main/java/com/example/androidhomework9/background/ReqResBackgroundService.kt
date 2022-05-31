package com.example.androidhomework9.background

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.api.dto.response.DataItem
import com.example.androidhomework9.data.UserDatabase

class ReqResBackgroundService(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {
    private var users = mutableListOf<DataItem>()

    override fun doWork(): Result {
        return try {
            RestClient.reqResService.getUsers(1).execute().body()?.data.let {
                users += it as MutableList<DataItem>
            }

            RestClient.reqResService.getUsers(2).execute().body()?.data.let {
                users += it as MutableList<DataItem>
            }

            RestClient.reqResService.getUsers(3).execute().body()?.data.let {
                users += it as MutableList<DataItem>
            }

            Log.d("USERS", users.toString())
            users.forEach {
                Log.d("USER", it.toString())
                UserDatabase.invoke(applicationContext).dataItemDao().insertAll(it)
            }
            return Result.success()
        } catch (e: RuntimeException) {
            Log.e("USERS", "Error can't get users ${e.message.toString()}")
            Result.failure()
        }
    }
}