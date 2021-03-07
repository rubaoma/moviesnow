package com.rubdev.moviesnow.di

import com.rubdev.moviesnow.repository.MoviesRepository
import dagger.Component
import javax.inject.Singleton



    @Singleton
    @Component(modules = [AppModule::class])
    interface AppComponent{

        fun inject(moviesRepository: MoviesRepository)
    }
