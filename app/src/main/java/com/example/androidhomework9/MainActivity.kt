package com.example.androidhomework9

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.background.ReqResBackgroundService

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        RestClient.initClient()

        var intent = Intent(this, ReqResBackgroundService::class.java)
        startService(intent)
    }
}