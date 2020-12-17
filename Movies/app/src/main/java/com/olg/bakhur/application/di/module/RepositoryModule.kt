package com.olg.bakhur.application.di.module

import com.olg.bakhur.data.repository.MovieRepositoryImpl
import com.olg.bakhur.data.source.remote.MovieApi
import com.olg.bakhur.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides

@Module
object RepositoryModule {

    @Provides
    fun provideMovieRepository(
        api: MovieApi
    ): MovieRepository = MovieRepositoryImpl(api)
}