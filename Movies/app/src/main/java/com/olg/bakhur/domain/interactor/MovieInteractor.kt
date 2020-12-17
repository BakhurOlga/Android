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
    suspend fun getMovieDetails(id: Int, apiKey: String): Result<MovieDetails> {
        return movieUseCase.getMovieDetails(id, apiKey)
    }

    suspend fun getNowPlayingMoviesList(apiKey: String): Result<List<NowPlayingMovie>> {
        return movieUseCase.getNowPlayingMoviesList(apiKey)
    }

    suspend fun getPopularMoviesList(apiKey: String): Result<List<PopularMovie>> {
        return movieUseCase.getPopularMoviesList(apiKey)
    }

    suspend fun getUpcomingMoviesList(apiKey: String): Result<List<UpcomingMovie>> {
        return movieUseCase.getUpcomingMoviesList(apiKey)
    }
}