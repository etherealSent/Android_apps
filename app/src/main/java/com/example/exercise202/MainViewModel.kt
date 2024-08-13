package com.example.exercise202

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.exercise202.repository.DogRepository
import com.example.exercise202.repository.DogUi
import com.example.exercise202.repository.Result

class MainViewModel(
    private val repository: DogRepository
) : ViewModel() {

    private val _dogsLiveData: MediatorLiveData<Result<List<DogUi>>> by lazy {
        MediatorLiveData<Result<List<DogUi>>>()
    }
    val dogsLiveData: LiveData<Result<List<DogUi>>> = _dogsLiveData

    private val _downloadResult: MediatorLiveData<Result<Unit>> by lazy {
        MediatorLiveData<Result<Unit>>()
    }
    val downloadResult: LiveData<Result<Unit>> = _downloadResult

    fun getDogs() {
        _dogsLiveData.addSource(repository.loadDogList()) {
            _dogsLiveData.postValue(it)
        }
    }

    fun downloadFile(url: String) {
        _downloadResult.addSource(repository.downloadFile(url)) {
            _downloadResult.postValue(it)
        }
    }
}