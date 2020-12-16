package com.olg.bakhur.data.repository

import com.olg.bakhur.data.mapper.mapToMovieDetails
import com.olg.bakhur.data.mapper.mapToNowPlayingMovieList
import com.olg.bakhur.data.mapper.mapToPopularMovieList
import com.olg.bakhur.data.mapper.mapToUpcomingMovieList
import com.olg.bakhur.data.source.remote.MovieApi
import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApi
) : MovieRepository {

    override suspend fun getMovieDetails(id: Int): Result<MovieDetails> {
        return safeApiCall { api.getMovieDetails(id).mapToMovieDetails() }
    }

    override suspend fun getNowPlayingMoviesList(): Result<List<PopularMovie>> {
        return safeApiCall { api.getNowPlayingMoviesList.mapToNowPlayingMovieList() }
    }

    override suspend fun getPopularMoviesList(): Result<List<NowPlayingMovie>> {
        return safeApiCall { api.getPopularMoviesList.mapToPopularMovieList() }
    }

    override suspend fun getUpcomingMoviesList(): Result<List<UpcomingMovie>> {
        return safeApiCall { api.getUpcomingMoviesList.mapToUpcomingMovieList() }
    }
}