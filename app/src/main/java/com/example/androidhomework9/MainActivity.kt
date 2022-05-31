package com.example.androidhomework9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.background.DatabaseBackgroundService
import com.example.androidhomework9.background.ReqResBackgroundService
import com.example.androidhomework9.data.UserDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        UserDatabase.invoke(applicationContext).dataItemDao().deleteAll()

        RestClient.initClient()

        val c = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val listUsers = OneTimeWorkRequestBuilder<ReqResBackgroundService>()
            .setConstraints(c)
            .build()

        val usersDeleteProcedure = OneTimeWorkRequestBuilder<DatabaseBackgroundService>()
            .build()

        WorkManager.getInstance(this)
            .beginWith(listUsers)
            .then(usersDeleteProcedure)
            .enqueue()
    }
}