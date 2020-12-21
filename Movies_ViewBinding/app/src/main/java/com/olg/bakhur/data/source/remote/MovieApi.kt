package com.olg.bakhur.data.source.remote

import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.model.pojo.movie.NowPlayingMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.PopularMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.UpcomingMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/{id}")
    suspend fun getMovieDetails(@Path(value = "id") id: Int, @Query("api_key") api_key: String): MovieDetailsResponse

    @GET("movie/top_rated")
    suspend fun getPopularMoviesList(@Query("api_key") apiKey: String): PopularMovieListResponse

    @GET("movie/now_playing")
    suspend fun getNowPlayingMoviesList(@Query("api_key") apiKey: String): NowPlayingMovieListResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMoviesList(@Query("api_key") apiKey: String): UpcomingMovieListResponse
}