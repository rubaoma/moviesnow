package com.rubdev.moviesnow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubdev.moviesnow.data.ImdbApi
import com.rubdev.moviesnow.di.DaggerAppComponent
import com.rubdev.moviesnow.model.MovieResponse
import dagger.android.DaggerApplication
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MoviesRepository {

    @Inject
    lateinit var IMDBApiService: ImdbApi

    private val _movies by lazy { MutableLiveData<List<MovieResponse>>() }
    val movie: LiveData<List<MovieResponse>>
        get() = _movies

    private val _isProgress by lazy { MutableLiveData<Boolean>() }
    val isInProgress: LiveData<Boolean>
        get() = _isProgress

    private val _isError by lazy { MutableLiveData<Boolean>() }
        val isError: LiveData<Boolean>
        get() = _isError


    init {
        DaggerAppComponent.create().inject(this)
    }


}