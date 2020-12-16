package com.olg.bakhur.presentation.ui.now_playing

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.repository.MovieRepositoryImpl
import com.olg.bakhur.domain.interactor.MovieInteractor
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class NowPlayingMovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    val nowPlayingMovieList = liveData(Dispatchers.IO) {
        val retrivedLatestMoviesList = interactor.getNowPlayingMoviesList()

        emit(retrivedLatestMoviesList)
    }
}