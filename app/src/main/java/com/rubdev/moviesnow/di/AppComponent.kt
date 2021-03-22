package com.rubdev.moviesnow.di

import com.rubdev.moviesnow.repository.MoviesRepository
import com.rubdev.moviesnow.view.ui.MainActivity
import com.rubdev.moviesnow.viewmodel.MovieViewModel
import dagger.Component
import javax.inject.Singleton



    @Singleton
    @Component(modules = [AppModule::class])
    interface AppComponent{

        fun inject(moviesRepository: MoviesRepository)

        fun inject(viewModel: MovieViewModel)

        fun inject(mainActivity: MainActivity)

    }
