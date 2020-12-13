package com.olg.bakhur.data.server_pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NowPlayingMovies(
    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("poster_path")
    @Expose
    val posterPath: String,

    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
)
