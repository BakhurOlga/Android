package com.olg.bakhur.data

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/top_rated")
    suspend fun getPopularMoviesList(): PopularMovieList

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path(value = "id") id: Int): MovieDetails

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesList(): NowPlayingMovieList

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesList(): UpcomingMovieList
}