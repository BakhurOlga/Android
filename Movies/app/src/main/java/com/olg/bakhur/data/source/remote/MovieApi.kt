package com.olg.bakhur.data.source.remote

import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.model.pojo.movie.NowPlayingMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.PopularMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.UpcomingMovieListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

//    @GET("movie/{id}")
//    suspend fun getMovieDetails(@Path(value = "id") id: Int): MovieDetailsResponse
//
//    @GET("movie/top_rated")
//    suspend fun getPopularMoviesList(): PopularMovieListResponse
//
//    @GET("movie/now_playing")
//    suspend fun getNowPlayingMoviesList(): NowPlayingMovieListResponse
//
//    @GET("movie/upcoming")
//    suspend fun getUpcomingMoviesList(): UpcomingMovieListResponse

    @GET("movie/{id}/{api_key}")
    suspend fun getMovieDetails(@Path(value = "id") id: Int, @Path(value = "api_key") api_key: String): MovieDetailsResponse

    @GET("movie/top_rated/{api_key}")
    suspend fun getPopularMoviesList(@Path(value = "api_key") api_key: String): PopularMovieListResponse

    @GET("movie/now_playing/{api_key}")
    suspend fun getNowPlayingMoviesList(@Path(value = "api_key") api_key: String): NowPlayingMovieListResponse

    @GET("movie/upcoming/{api_key}")
    suspend fun getUpcomingMoviesList(@Path(value = "api_key") api_key: String): UpcomingMovieListResponse
}