package com.olg.bakhur.data.repositories

import com.olg.bakhur.application.RetrofitService

class MovieRepository {

    val apiService = RetrofitService.apiService

    suspend fun getMovieDetails(id: Int) = apiService.getMovieDetails(id)
    suspend fun getPopularMoviesList() = apiService.getPopularMoviesList()
    suspend fun getNowPlayingMoviesList() = apiService.getNowPlayingMoviesList()
    suspend fun getUpcomingMoviesList() = apiService.getUpcomingMoviesList()
}