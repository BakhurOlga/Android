package com.olg.bakhur.data.model.pojo.movie

import com.google.gson.annotations.SerializedName
import com.olg.bakhur.data.server_pojo.UpcomingMovieResponse

data class UpcomingMovieListResponse(
    @SerializedName("results")
    val upcomingMovieList: List<UpcomingMovieResponse>
)
