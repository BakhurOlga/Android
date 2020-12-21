package com.olg.bakhur.data.model.pojo.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("budget")
    val budget: Int,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("release_date")
    val releaseDate: String
)