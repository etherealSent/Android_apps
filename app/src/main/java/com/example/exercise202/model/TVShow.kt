package com.example.exercise202.model

import com.squareup.moshi.Json

data class TVShow(
    val adult: Boolean = false,
    @field:Json(name = "backdrop_path")
    val backdropPath: String = "",
    @field:Json(name = "genre_ids")
    val genreIds: List<Int> = listOf(),
    val id: Int = 0,
    @field:Json(name = "origin_country")
    val originCountry: List<String> = listOf(),
    @field:Json(name = "original_language")
    val originalLanguage: String = "",
    @field:Json(name = "original_name")
    val originalName: String = "",
    val overview: String = "",
    val popularity: Double = 0.0,
    @field:Json(name = "poster_path")
    val posterPath: String = "",
    @field:Json(name = "first_air_date")
    val firstAirDate: String = "",
    val name: String = "",
    @field:Json(name = "vote_average")
    val voteAverage: Double = 0.0,
    @field:Json(name = "vote_count")
    val voteCount: Int = 0
)