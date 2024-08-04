package com.example.exercise202.model

sealed class ListItemUiModel {
    data class Title(val title: String) : ListItemUiModel()
    data class Cat(val cat: CatUiModel) : ListItemUiModel()
}