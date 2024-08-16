package com.example.exercise202

import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.exercise202.api.TelevisionService
import com.example.exercise202.database.TVShowDatabase
import com.example.exercise202.repository.TVShowRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class TVApplication : Application() {
    lateinit var repository: TVShowRepository
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        val tvService = retrofit.create(TelevisionService::class.java)
        val tvShowDatabase = TVShowDatabase.getInstance(applicationContext)
        repository = TVShowRepository(tvService, tvShowDatabase)

        val constraints = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()

        val workRequest = PeriodicWorkRequest
            .Builder(TVShowWorker::class.java, 1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .addTag("movie-work").build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}