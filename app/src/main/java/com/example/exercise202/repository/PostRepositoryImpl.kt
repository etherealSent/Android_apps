package com.example.exercise202.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercise202.api.JsonPHService
import com.example.exercise202.api.Post
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepositoryImpl(
    private val jsonPHService: JsonPHService
) : PostRepository {
    override fun loadPostList(): LiveData<List<Post>> {
        val result = MutableLiveData<List<Post>>()

        jsonPHService.getPosts().enqueue(object : Callback<List<Post>> {

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
            }

            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    result.postValue(response.body())
                }
            }
        })
        return result
    }

}