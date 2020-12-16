package com.olg.bakhur.data.source.remote

import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.model.pojo.movie.NowPlayingMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.PopularMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.UpcomingMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path(value = "id") id: Int): MovieDetailsResponse

    @GET("movie/top_rated")
    suspend fun getPopularMoviesList(): PopularMovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesList(): NowPlayingMovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesList(): UpcomingMovieListResponse
}