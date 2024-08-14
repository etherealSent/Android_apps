package com.example.exercise202.api

import retrofit2.Call
import retrofit2.http.GET

interface JsonPHService {
    @GET("posts")
    fun getPosts(): Call<List<Post>>
}