package com.olg.bakhur.presentation.ui.details

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olg.bakhur.domain.interactor.MovieInteractor
import com.olg.bakhur.domain.model.dto.MovieDetails
import com.olg.bakhur.domain.model.result.onError
import com.olg.bakhur.domain.model.result.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    var movieDetailsMutableLD = MutableLiveData<MovieDetails>()
    val movieDetailsLD: LiveData<MovieDetails> = movieDetailsMutableLD

    fun getMovieDetails(id: Int, apiKey: String): LiveData<MovieDetails> {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getMovieDetails(id, apiKey)
                .onSuccess { movieDetailsMutableLD.postValue(it) }
                .onError { it -> Log.d("TAG", "ERROR: ${it.message}") }
        }
        return movieDetailsLD
    }
}