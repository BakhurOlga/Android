package com.olg.bakhur.data.server_pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PopularMovieList(
    @SerializedName("results")
    @Expose
    val popularMovieList: MutableList<PopularMovies>
)