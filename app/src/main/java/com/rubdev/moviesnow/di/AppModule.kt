package com.rubdev.moviesnow.di

import com.rubdev.moviesnow.data.ImdbApi
import com.rubdev.moviesnow.data.network.IMDBApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideApi(): ImdbApi = IMDBApiService.getClient()

}