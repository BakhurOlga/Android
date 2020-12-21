package com.olg.bakhur.data.model.pojo.movie

import com.google.gson.annotations.SerializedName
import com.olg.bakhur.data.server_pojo.PopularMovieResponse

data class PopularMovieListResponse(
    @SerializedName("results")
    val popularMovieList: List<PopularMovieResponse>
)