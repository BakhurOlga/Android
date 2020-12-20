package com.olg.bakhur.presentation.ui.upcoming

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olg.bakhur.domain.interactor.MovieInteractor
import com.olg.bakhur.domain.model.dto.UpcomingMovie
import com.olg.bakhur.domain.model.result.onError
import com.olg.bakhur.domain.model.result.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class UpcomingMovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    var upcomingMoviesListMutableLD = MutableLiveData<List<UpcomingMovie>>()
    val upcomingMoviesListListLD: LiveData<List<UpcomingMovie>> = upcomingMoviesListMutableLD

    fun getUpcomingMoviesList(apiKey: String): LiveData<List<UpcomingMovie>> {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getUpcomingMoviesList(apiKey)
                .onSuccess { upcomingMoviesListMutableLD.postValue(it) }
                .onError { Log.d("TAG", "ERROR: ${it.message}") }
        }
        return upcomingMoviesListListLD
    }
}