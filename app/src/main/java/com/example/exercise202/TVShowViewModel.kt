package com.example.exercise202

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise202.model.TVShow
import com.example.exercise202.repository.TVShowRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TVShowViewModel(
    private val repository: TVShowRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        fetchTVShows()
    }

    private val _tvShows = MutableStateFlow(emptyList<TVShow>())

    val tvShows: StateFlow<List<TVShow>> =
        _tvShows

    private val _error = MutableStateFlow("")

    val error: StateFlow<String> =
        _error

    fun fetchTVShows() {
        viewModelScope.launch(dispatcher) {
            repository.fetchTVShows().catch {
                _error.value =
                    "An exception occurred: ${it.message}"
            }.collect {
                _tvShows.value = it
            }
        }
    }

}