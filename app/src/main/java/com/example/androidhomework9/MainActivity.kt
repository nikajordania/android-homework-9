package com.example.androidhomework9

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.androidhomework9.adapters.UserRecyclerAdapter
import com.example.androidhomework9.api.dto.RestClient
import com.example.androidhomework9.api.dto.response.DataItem
import com.example.androidhomework9.background.DatabaseBackgroundService
import com.example.androidhomework9.background.ReqResBackgroundService
import com.example.androidhomework9.data.UserDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private val workManager = WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
//        UserDatabase.invoke(applicationContext).dataItemDao().deleteAll()

        RestClient.initClient()

        val c = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED).build()

        val listUsers = OneTimeWorkRequestBuilder<ReqResBackgroundService>()
            .setConstraints(c)
            .build()

        val usersDeleteProcedure = OneTimeWorkRequestBuilder<DatabaseBackgroundService>()
            .build()

        workManager
            .beginWith(listUsers)
            .then(usersDeleteProcedure)
            .enqueue()


        workManager.getWorkInfoByIdLiveData(usersDeleteProcedure.id)
            .observe(this, Observer { workInfo ->
                if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                    getAllUsers()
                }
            })
    }

    private fun getAllUsers() {

        Thread(Runnable {
            val allRecords: List<DataItem> =
                UserDatabase.invoke(applicationContext).dataItemDao().getAll()

            Log.d(
                "FINALLY",
                allRecords.toString()
            )

//            recyclerView.adapter = UserRecyclerAdapter(allRecords)
//            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }).start()

    }
}
