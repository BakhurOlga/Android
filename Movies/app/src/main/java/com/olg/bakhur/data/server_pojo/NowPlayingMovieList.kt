package com.olg.bakhur.data.server_pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class NowPlayingMovieList(
    @SerializedName("results")
    @Expose
    val nowPlayingMovieList: MutableList<NowPlayingMovies>
)