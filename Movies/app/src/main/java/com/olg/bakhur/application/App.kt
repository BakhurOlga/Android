package com.olg.bakhur.application

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.olg.bakhur.application.di.component.AppComponent
import com.olg.bakhur.application.di.component.DaggerAppComponent
import com.olg.bakhur.presentation.ui.details.MovieDetailsFragment
import com.olg.bakhur.presentation.ui.details.MovieDetailsViewModel
import com.olg.bakhur.presentation.ui.now_playing.NowPlayingMovieListFragment
import com.olg.bakhur.presentation.ui.now_playing.NowPlayingMovieViewModel
import com.olg.bakhur.presentation.ui.popular.PopularMovieListFragment
import com.olg.bakhur.presentation.ui.popular.PopularMovieViewModel
import com.olg.bakhur.presentation.ui.upcoming.UpcomingMovieListFragment
import com.olg.bakhur.presentation.ui.upcoming.UpcomingMovieViewModel

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var component: AppComponent
            private set
    }
}