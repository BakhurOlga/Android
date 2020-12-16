package com.olg.bakhur.presentation.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.repository.MovieRepositoryImpl
import com.olg.bakhur.domain.interactor.MovieInteractor
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class PopularMovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
): ViewModel() {

    val popularMovieList = liveData(Dispatchers.IO) {
        val retrivedPopularMoviesList = interactor.getPopularMoviesList()

        emit(retrivedPopularMoviesList)
    }
}