package com.olg.bakhur.application.di.module

import com.olg.bakhur.data.source.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [HttpModule::class])
object ApiModule {

//    @Singleton //???
    @Reusable
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}