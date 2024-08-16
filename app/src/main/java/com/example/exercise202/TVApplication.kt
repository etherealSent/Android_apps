package com.example.exercise202

import android.app.Application
import com.example.exercise202.api.TelevisionService
import com.example.exercise202.repository.TVShowRepository
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class TVApplication : Application() {
    lateinit var repository: TVShowRepository
    override fun onCreate() {
        super.onCreate()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        val tvService = retrofit.create(TelevisionService::class.java)
        repository = TVShowRepository(tvService)
    }
}