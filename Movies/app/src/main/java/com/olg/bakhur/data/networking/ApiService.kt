package com.olg.bakhur.data.networking

import com.olg.bakhur.data.server_pojo.MovieDetails
import com.olg.bakhur.data.server_pojo.NowPlayingMovieList
import com.olg.bakhur.data.server_pojo.PopularMovieList
import com.olg.bakhur.data.server_pojo.UpcomingMovieList
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