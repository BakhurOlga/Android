package com.olg.bakhur.presentation.ui.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.repository.MovieRepositoryImpl
import com.olg.bakhur.domain.interactor.MovieInteractor
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class UpcomingMovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
): ViewModel() {

    val upcomingMoviesList = liveData(Dispatchers.IO) {
        val retrivedUpcomingMoviesList = interactor.getUpcomingMoviesList()

        emit(retrivedUpcomingMoviesList)
    }
}