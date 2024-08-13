package com.example.exercise202.repository

import com.example.exercise202.api.Dog
import com.example.exercise202.storage.room.DogEntity

class DogMapper {

    fun mapServiceToEntity(dog: Dog) = dog.message.map {
        DogEntity(0, it)
    }

    fun mapEntityToUiModel(dogEntity: DogEntity) = DogUi(dogEntity.url)
}
