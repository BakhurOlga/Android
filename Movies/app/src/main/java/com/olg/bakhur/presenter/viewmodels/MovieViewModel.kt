package com.olg.bakhur.presenter.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.Dispatchers

class MovieViewModel : ViewModel() {

    val repository: MovieRepositoryImpl = MovieRepositoryImpl() // inject. Field injection should only be used in Android framework classes where constructor injection cannot be used.

    fun getMovieDetails(id: Int): LiveData<MovieDetailsResponse> {
        val movie = liveData(Dispatchers.IO) {
            val retrivedMovie = repository.getMovieDetails(id)

            emit(retrivedMovie)
        }
        return movie
    }

    val popularMovieList = liveData(Dispatchers.IO) {
        val retrivedPopularMoviesList = repository.getPopularMoviesList()

        emit(retrivedPopularMoviesList)
    }

    val nowPlayingMovieList = liveData(Dispatchers.IO) {
        val retrivedLatestMoviesList = repository.getNowPlayingMoviesList()

        emit(retrivedLatestMoviesList)
    }

    val upcomingMoviesList = liveData(Dispatchers.IO) {
        val retrivedUpcomingMoviesList = repository.getUpcomingMoviesList()

        emit(retrivedUpcomingMoviesList)
    }
}