package com.example.exercise202

import androidx.lifecycle.LiveData
import com.example.exercise202.api.Post
import com.example.exercise202.repository.PostRepository
import junit.framework.TestCase.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest {

    @InjectMocks
    lateinit var mainViewModel: MainViewModel

    @Mock
    lateinit var repository: PostRepository

    @Test
    fun getPosts() {
        val expected = mock<LiveData<List<Post>>>()

        whenever(mainViewModel.getPosts()).thenReturn(expected)

        val result = mainViewModel.getPosts()

        assertEquals(expected, result)
    }
}