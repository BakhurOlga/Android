package com.olg.bakhur.domain.repository

import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.domain.model.result.Result

interface MovieRepository {

    suspend fun getMovieDetails(id: Int): Result<MovieDetails>
    suspend fun getNowPlayingMoviesList(): Result<List<NowPlayingMovie>>
    suspend fun getPopularMoviesList(): Result<List<PopularMovie>>
    suspend fun getUpcomingMoviesList(): Result<List<UpcomingMovie>>
}