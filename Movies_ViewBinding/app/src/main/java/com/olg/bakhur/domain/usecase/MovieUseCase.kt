package com.olg.bakhur.domain.usecase

import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.domain.model.result.Result
import com.olg.bakhur.domain.repository.MovieRepository
import javax.inject.Inject

class MovieUseCase @Inject constructor(
    private val repository: MovieRepository
){
    suspend fun getMovieDetails(id: Int, apiKey: String): Result<MovieDetails>{
        return repository.getMovieDetails(id, apiKey)
    }

    suspend fun getNowPlayingMoviesList(apiKey: String): Result<List<NowPlayingMovie>>{
        return repository.getNowPlayingMoviesList(apiKey)
    }

    suspend fun getPopularMoviesList(apiKey: String): Result<List<PopularMovie>>{
        return repository.getPopularMoviesList(apiKey)
    }

    suspend fun getUpcomingMoviesList(apiKey: String): Result<List<UpcomingMovie>>{
        return repository.getUpcomingMoviesList(apiKey)
    }
}