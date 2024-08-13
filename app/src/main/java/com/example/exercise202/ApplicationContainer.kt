package com.example.exercise202

import kotlin.random.Random

class ApplicationContainer {
    val numberRepository: NumberRepository = NumberRepositoryImpl(Random)

}