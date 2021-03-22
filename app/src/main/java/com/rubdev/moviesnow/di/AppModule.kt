package com.rubdev.moviesnow.di

import com.rubdev.moviesnow.data.ImdbApi
import com.rubdev.moviesnow.data.network.IMDBApiService
import com.rubdev.moviesnow.model.Result
import com.rubdev.moviesnow.repository.MoviesRepository
import com.rubdev.moviesnow.view.MovieAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): ImdbApi = IMDBApiService.getClient()

    @Provides
    fun provideMoviesRepository() = MoviesRepository()

    @Provides
    fun provideListMovie() = ArrayList<Result>()

    @Provides
    fun provideMoviesAdapter(result: ArrayList<Result>): MovieAdapter = MovieAdapter(result)

}