package com.example.exercise202.model

data class CatUiModel(
    val gender: Gender,
    val breed: CatBreed,
    val name: String,
    val biography: String,
    val imageUrl: String
)