package com.olg.bakhur.domain.repository

import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.domain.model.result.Result

interface MovieRepository {

    suspend fun getMovieDetails(id: Int, apiKey: String): Result<MovieDetails>
    suspend fun getNowPlayingMoviesList(apiKey: String): Result<List<NowPlayingMovie>>
    suspend fun getPopularMoviesList(apiKey: String): Result<List<PopularMovie>>
    suspend fun getUpcomingMoviesList(apiKey: String): Result<List<UpcomingMovie>>
}