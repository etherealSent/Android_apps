package com.example.exercise202.api

import com.example.exercise202.model.TVShowResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TelevisionService {
    @GET("tv/on_the_air")
    suspend fun getTVShows(@Query("api_key") apiKey: String): TVShowResponse
}