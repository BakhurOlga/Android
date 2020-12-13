package com.olg.bakhur.data.server_pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class MovieDetails(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String,

    @SerializedName("overview")
    @Expose
    val overview: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,

    @SerializedName("budget")
    @Expose
    val budget: Int,

    @SerializedName("original_language")
    @Expose
    val originalLanguage: String,

    @SerializedName("popularity")
    @Expose
    val popularity: Double,

    @SerializedName("release_date")
    @Expose
    val releaseDate: String
)