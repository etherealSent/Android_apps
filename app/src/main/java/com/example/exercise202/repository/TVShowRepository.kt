package com.example.exercise202.repository

import android.util.Log
import com.example.exercise202.api.TelevisionService
import com.example.exercise202.database.TVShowDao
import com.example.exercise202.database.TVShowDatabase
import com.example.exercise202.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val apiKey = "2f20c6bdb7a2c4a93df34a3114f9d912"

class TVShowRepository(
    private val tvService: TelevisionService,
    private val tvShowDatabase: TVShowDatabase
) {
    fun fetchTVShows() : Flow<List<TVShow>> {
        return flow {
            val tvShowDao = tvShowDatabase.tvShowDao()
            val savedMovies = tvShowDao.getTVShows()
            if (savedMovies.isEmpty()) {
                val tvShows = tvService.getTVShows(apiKey).results
                tvShowDao.addTVSHows(tvShows)
                emit(tvShows)
            } else {
                emit(savedMovies)
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun fetchTVShowsFromNetwork() {
        val tvShowDao: TVShowDao = tvShowDatabase.tvShowDao()
        try {
            val tvShows = tvService.getTVShows(apiKey).results

            tvShowDao.addTVSHows(tvShows)
        } catch (exception: Exception) {
            Log.d("TVShowRepository", "An error occurred:${exception.message}")
        }
    }


}