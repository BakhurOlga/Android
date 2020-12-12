package com.olg.bakhur.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UpcomingMovieList(
    @SerializedName("results")
    @Expose
    val upcomingMovieList: MutableList<UpcomingMovie>
)
