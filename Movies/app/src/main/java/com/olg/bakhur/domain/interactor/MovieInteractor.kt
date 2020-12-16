package com.olg.bakhur.domain.interactor

import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.domain.model.result.Result
import com.olg.bakhur.domain.usecase.MovieUseCase
import javax.inject.Inject

class MovieInteractor @Inject constructor(
    private val movieUseCase: MovieUseCase
) {
    suspend fun getMovieDetails(id: Int): Result<MovieDetails> {
        return movieUseCase.getMovieDetails(id)
    }

    suspend fun getNowPlayingMoviesList(): Result<List<NowPlayingMovie>> {
        return movieUseCase.getNowPlayingMoviesList()
    }

    suspend fun getPopularMoviesList(): Result<List<PopularMovie>> {
        return movieUseCase.getPopularMoviesList()
    }

    suspend fun getUpcomingMoviesList(): Result<List<UpcomingMovie>> {
        return movieUseCase.getUpcomingMoviesList()
    }
}