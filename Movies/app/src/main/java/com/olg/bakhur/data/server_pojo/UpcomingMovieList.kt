package com.olg.bakhur.data.server_pojo

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UpcomingMovieList(
    @SerializedName("results")
    @Expose
    val upcomingMovieList: MutableList<UpcomingMovie>
)
