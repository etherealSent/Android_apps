package com.example.exercise202.repository

import androidx.lifecycle.LiveData

interface DogRepository {

    fun loadDogList() : LiveData<Result<List<DogUi>>>

    fun downloadFile(url: String): LiveData<Result<Unit>>
}