package com.example.exercise202.repository

import androidx.lifecycle.LiveData
import com.example.exercise202.api.Post

interface PostRepository {

    fun loadPostList() : LiveData<List<Post>>
}