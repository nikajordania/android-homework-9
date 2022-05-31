package com.example.androidhomework9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.background.ReqResBackgroundService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        RestClient.initClient()

        val listUsers = OneTimeWorkRequestBuilder<ReqResBackgroundService>()
            .build()

        WorkManager.getInstance(this)
            .beginWith(listUsers).enqueue().result
    }
}