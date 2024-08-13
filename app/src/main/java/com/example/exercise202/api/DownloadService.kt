package com.example.exercise202.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Url

interface DownloadService {
    @GET("breed/hound/images/random/{number}")
    fun getDogs(@Path("number") number: Int): Call<Dog>

    @GET
    fun downloadFile(@Url fileUrl: String): Call<ResponseBody>
}