package com.rubdev.moviesnow.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubdev.moviesnow.Constants.Companion.PAGE
import com.rubdev.moviesnow.Constants.Companion.PT_BR
import com.rubdev.moviesnow.data.ImdbApi
import com.rubdev.moviesnow.di.DaggerAppComponent
import com.rubdev.moviesnow.internal.KEY_IMDB
import com.rubdev.moviesnow.model.MovieResponse
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MoviesRepository {

    @Inject
    lateinit var iMDBApiService: ImdbApi

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

    private fun insertMovie(): Disposable {
        return iMDBApiService.getMoviesNowPlaying(KEY_IMDB, PT_BR, PAGE)
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase():DisposableSubscriber<MovieResponse>{
        return object : DisposableSubscriber<MovieResponse>(){
            override fun onNext(movieResponse: MovieResponse?) {
                if (movieResponse != null){
                    val entityList = movieResponse.results.toList()
                }
            }

            override fun onError(t: Throwable?) {
                TODO("Not yet implemented")
            }

            override fun onComplete() {
                TODO("Not yet implemented")
            }

        }
    }

}