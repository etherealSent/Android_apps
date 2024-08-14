package com.example.exercise202.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.exercise202.api.JsonPHService
import com.example.exercise202.api.Post
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class PostRepositoryImplTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @InjectMocks
    lateinit var repository: PostRepositoryImpl

    @Mock
    lateinit var jsonPHService: JsonPHService

    @Mock
    lateinit var call: Call<List<Post>>

    @Before
    fun setUp() {
        whenever(jsonPHService.getPosts()).thenReturn(call)
    }

    @Test
    fun getPost_success() {
        val postList = listOf(
            Post(1,1,"dd", "ss"),
            Post(2,1,"dd", "ss"),
            Post(3,1,"dd", "ss"),
        )
        whenever(call.enqueue(any())).thenAnswer {
            (it.arguments[0] as Callback<List<Post>>).onResponse(call, Response.success(postList))
        }

        val result = repository.loadPostList()

        assertEquals(postList, result.value)
    }
}