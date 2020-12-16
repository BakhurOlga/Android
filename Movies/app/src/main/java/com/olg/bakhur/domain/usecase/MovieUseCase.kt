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
    suspend fun getMovieDetails(id: Int): Result<MovieDetails>{
        return repository.getMovieDetails(id)
    }

    suspend fun getNowPlayingMoviesList(): Result<List<NowPlayingMovie>>{
        return repository.getNowPlayingMoviesList()
    }

    suspend fun getPopularMoviesList(): Result<List<PopularMovie>>{
        return repository.getPopularMoviesList()
    }

    suspend fun getUpcomingMoviesList(): Result<List<UpcomingMovie>>{
        return repository.getUpcomingMoviesList()
    }
}