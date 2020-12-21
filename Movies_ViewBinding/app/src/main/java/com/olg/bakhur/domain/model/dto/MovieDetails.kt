package com.olg.bakhur.domain.model.dto

data class MovieDetails(
    val id: Int,
    val title: String,
    val posterPath: String,
    val overview: String,
    val voteAverage: Double,
    val budget: Int,
    val originalLanguage: String,
    val popularity: Double,
    val releaseDate: String
)
