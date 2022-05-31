package com.example.androidhomework9.background

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.HandlerThread
import android.os.IBinder
import android.util.Log
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.api.dto.response.ListUsers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReqResBackgroundService : Service() {
    private lateinit var handler: Handler
    private lateinit var handleThread: HandlerThread

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        handleThread = HandlerThread("ReqRes Service")
        handleThread.start()
        handler = Handler(handleThread.looper)
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        handler.post {
            RestClient.reqResService.getUsers(2).enqueue(object : Callback<ListUsers> {
                override fun onResponse(
                    call: Call<ListUsers>, response: Response<ListUsers>
                ) {
                    if (response.isSuccessful) {
                        Log.d("Successful", response.body().toString())
//                        result.text = response.body().toString()
                    }
                }

                override fun onFailure(call: Call<ListUsers>, t: Throwable) {
                    Log.d("Failure", t.message.toString())
                }
            })
        }

        return super.onStartCommand(intent, flags, startId)
    }
}