package com.olg.bakhur.data.mapper

import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.model.pojo.movie.NowPlayingMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.PopularMovieListResponse
import com.olg.bakhur.data.model.pojo.movie.UpcomingMovieListResponse
import com.olg.bakhur.data.server_pojo.NowPlayingMovieResponse
import com.olg.bakhur.data.server_pojo.PopularMovieResponse
import com.olg.bakhur.data.server_pojo.UpcomingMovieResponse
import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.dto.UpcomingMovie

// items mappers
fun MovieDetailsResponse.mapToMovieDetails() = MovieDetails(
    id = id,
    title = title,
    posterPath = posterPath,
    overview = overview,
    voteAverage = voteAverage,
    budget = budget,
    originalLanguage = originalLanguage,
    popularity = popularity,
    releaseDate = releaseDate
)

fun NowPlayingMovieResponse.mapToNowPlayingMovie() = NowPlayingMovie(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage
)

fun PopularMovieResponse.mapToPopularMovie() = PopularMovie(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage
)

fun UpcomingMovieResponse.mapToUpcomingMovie() = UpcomingMovie(
    id = id,
    title = title,
    posterPath = posterPath,
    voteAverage = voteAverage
)


// Lists mappers
fun NowPlayingMovieListResponse.mapToNowPlayingMovieList(): List<NowPlayingMovie>{
    return this.nowPlayingMovieList.map { movie ->
        movie.mapToNowPlayingMovie()
    }
}

fun PopularMovieListResponse.mapToPopularMovieList(): List<PopularMovie>{
    return this.popularMovieList.map { movie ->
        movie.mapToPopularMovie()
    }
}

fun UpcomingMovieListResponse.mapToUpcomingMovieList(): List<UpcomingMovie>{
    return this.upcomingMovieList.map { movie ->
        movie.mapToUpcomingMovie()
    }
}
