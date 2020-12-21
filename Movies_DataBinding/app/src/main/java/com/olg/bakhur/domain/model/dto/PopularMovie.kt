package com.olg.bakhur.domain.model.dto

data class PopularMovie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val voteAverage: Double,
)