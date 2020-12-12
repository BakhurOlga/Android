package com.olg.bakhur.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularMovieList(
    @SerializedName("results")
    @Expose
    val popularMovieList: MutableList<PopularMovies>
)