package com.example.exercise202.repository

import com.example.exercise202.api.TelevisionService
import com.example.exercise202.model.TVShow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

private const val apiKey = "2f20c6bdb7a2c4a93df34a3114f9d912"

class TVShowRepository(
    private val tvService: TelevisionService,
) {
    fun fetchTVShows() : Flow<List<TVShow>> {
        return flow {
            emit(tvService.getTVShows(apiKey).results)
        }.flowOn(Dispatchers.IO)
    }
}