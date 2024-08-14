package com.example.exercise202

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.exercise202.api.Post
import com.example.exercise202.repository.PostRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: PostRepository) : ViewModel() {
    fun getPosts() : LiveData<List<Post>> = repository.loadPostList()
}