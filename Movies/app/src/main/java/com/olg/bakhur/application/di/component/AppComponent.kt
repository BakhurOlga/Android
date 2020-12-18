package com.olg.bakhur.application.di.component

import android.app.Application
import com.olg.bakhur.application.di.module.ApiModule
import com.olg.bakhur.application.di.module.RepositoryModule
import com.olg.bakhur.presentation.ui.details.MovieDetailsViewModel
import com.olg.bakhur.presentation.ui.now_playing.NowPlayingMovieViewModel
import com.olg.bakhur.presentation.ui.popular.PopularMovieViewModel
import com.olg.bakhur.presentation.ui.upcoming.UpcomingMovieViewModel
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApiModule::class, RepositoryModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance app: Application): AppComponent
    }

    // ViewModels
    val movieDetailsViewModel: MovieDetailsViewModel
    val nowPlayingMovieViewModel: NowPlayingMovieViewModel
    val popularMovieViewModel: PopularMovieViewModel
    val upcomingMovieViewModel: UpcomingMovieViewModel
}