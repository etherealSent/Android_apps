package com.example.exercise202

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.exercise202.api.Post
import com.example.exercise202.repository.PostRepository

class DummyRepository : PostRepository {

    override fun loadPostList(): LiveData<List<Post>> {
        val liveData = MutableLiveData<List<Post>>()
        liveData.postValue(
            listOf(
                Post(1L, 1L, "Title 1", "Body 1"),
                Post(2L, 1L, "Title 2", "Body 2"),
                Post(3L, 1L, "Title 3", "Body 3")
            )
        )
        return liveData
    }
}