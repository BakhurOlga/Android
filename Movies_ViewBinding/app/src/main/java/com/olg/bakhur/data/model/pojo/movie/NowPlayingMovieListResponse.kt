package com.olg.bakhur.data.model.pojo.movie

import com.google.gson.annotations.SerializedName
import com.olg.bakhur.data.server_pojo.NowPlayingMovieResponse

data class NowPlayingMovieListResponse(
    @SerializedName("results")
    val nowPlayingMovieList: List<NowPlayingMovieResponse>
)