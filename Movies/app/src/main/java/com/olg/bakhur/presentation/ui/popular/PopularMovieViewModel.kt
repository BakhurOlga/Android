package com.olg.bakhur.presentation.ui.popular

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olg.bakhur.domain.interactor.MovieInteractor
import com.olg.bakhur.domain.model.dto.PopularMovie
import com.olg.bakhur.domain.model.result.onError
import com.olg.bakhur.domain.model.result.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularMovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    var popularMovieListMutableLD = MutableLiveData<List<PopularMovie>>()
    val popularMovieListListLD: LiveData<List<PopularMovie>> = popularMovieListMutableLD

    fun getPopularMovieList(apiKey: String): LiveData<List<PopularMovie>> {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getPopularMoviesList(apiKey)
                .onSuccess { popularMovieListMutableLD.postValue(it) }
                .onError { Log.d("TAG", "ERROR: ${it.message}") }
        }
        return popularMovieListListLD
    }
}