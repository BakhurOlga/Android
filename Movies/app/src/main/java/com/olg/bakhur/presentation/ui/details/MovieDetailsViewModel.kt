package com.olg.bakhur.presentation.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.olg.bakhur.data.model.pojo.movie.MovieDetailsResponse
import com.olg.bakhur.data.repository.MovieRepositoryImpl
import com.olg.bakhur.domain.interactor.MovieInteractor
import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.result.onError
import com.olg.bakhur.domain.model.result.onSuccess
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    fun getMovieDetails(id: Int): LiveData<MovieDetails> {
        val movie = liveData(Dispatchers.IO) {
            val retrivedMovie = interactor.getMovieDetails(id)

            emit(retrivedMovie)
        }
        return movie
    }
}