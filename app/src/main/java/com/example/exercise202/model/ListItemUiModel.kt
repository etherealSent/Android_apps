package com.example.exercise202.model

sealed class ListItemUiModel {
    data class Title(val title: Flavor) : ListItemUiModel()
    data class Recipe(val recipe: RecipeUiModel) : ListItemUiModel()
}