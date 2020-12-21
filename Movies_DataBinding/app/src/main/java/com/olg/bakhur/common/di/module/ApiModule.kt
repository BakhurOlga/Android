package com.olg.bakhur.common.di.module

import com.olg.bakhur.data.source.remote.MovieApi
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.Retrofit

@Module(includes = [HttpModule::class])
object ApiModule {

    @Reusable
    @Provides
    fun provideMovieApiService(retrofit: Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)
}