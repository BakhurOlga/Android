package com.olg.bakhur.presentation.ui.now_playing

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olg.bakhur.domain.interactor.MovieInteractor
import com.olg.bakhur.domain.model.dto.NowPlayingMovie
import com.olg.bakhur.domain.model.result.onError
import com.olg.bakhur.domain.model.result.onSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class NowPlayingMovieViewModel @Inject constructor(
    private val interactor: MovieInteractor
) : ViewModel() {

    var nowPlayingMovieListMutableLD = MutableLiveData<List<NowPlayingMovie>>()
    val nowPlayingMovieListLD: LiveData<List<NowPlayingMovie>> = nowPlayingMovieListMutableLD

    fun getNowPlayingMovieList(apiKey: String): LiveData<List<NowPlayingMovie>> {
        viewModelScope.launch(Dispatchers.IO) {
            interactor.getNowPlayingMoviesList(apiKey)
                .onSuccess { nowPlayingMovieListMutableLD.postValue(it) }
                .onError { it -> Log.d("TAG", "ERROR: ${it.message}") }
        }
        return nowPlayingMovieListLD
    }
}