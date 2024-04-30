package com.app.workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    private lateinit var periodicWorkRequest: PeriodicWorkRequest

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val text: TextView = findViewById(R.id.text)
        val start: TextView = findViewById(R.id.periodictext)
        val stop: TextView = findViewById(R.id.stopPeriodictext)
        text.setOnClickListener {
            val uploadWorkRequest: WorkRequest = OneTimeWorkRequestBuilder<WorkerClass>().build()
            WorkManager.getInstance(this).enqueue(uploadWorkRequest)
        }

        start.setOnClickListener {
            periodicWorkRequest =
                PeriodicWorkRequestBuilder<WorkerClass>(15, TimeUnit.MINUTES).build()
            WorkManager.getInstance(this).enqueue(periodicWorkRequest)
        }
        stop.setOnClickListener {
            Toast.makeText(this,"Stop",Toast.LENGTH_LONG).show()

            WorkManager.getInstance(this).cancelAllWork()
        }
    }
}