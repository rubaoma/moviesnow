package com.rubdev.moviesnow.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubdev.moviesnow.Constants.Companion.PAGE
import com.rubdev.moviesnow.Constants.Companion.PT_BR
import com.rubdev.moviesnow.MovieApplication
import com.rubdev.moviesnow.data.ImdbApi
import com.rubdev.moviesnow.data.database.toResultEntityList
import com.rubdev.moviesnow.data.database.toResultList
import com.rubdev.moviesnow.di.DaggerAppComponent
import com.rubdev.moviesnow.internal.KEY_IMDB
import com.rubdev.moviesnow.model.MovieResponse
import com.rubdev.moviesnow.model.Result
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
import javax.inject.Inject

class MoviesRepository {

    @Inject
    lateinit var iMDBApiService: ImdbApi

    private val _movies by lazy { MutableLiveData<List<Result>>() }
    val movies: LiveData<List<Result>>
        get() = _movies

    private val _isInProgress by lazy { MutableLiveData<Boolean>() }
    val isInProgress: LiveData<Boolean>
        get() = _isInProgress

    private val _isInError by lazy { MutableLiveData<Boolean>() }
    val isError: LiveData<Boolean>
        get() = _isInError


    init {
        DaggerAppComponent.create().inject(this)
    }

    private fun insertMovie(): Disposable {
        return iMDBApiService.getMoviesNowPlaying(KEY_IMDB, PT_BR, PAGE)
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase(): DisposableSubscriber<MovieResponse> {
        return object : DisposableSubscriber<MovieResponse>() {
            override fun onNext(movieResponse: MovieResponse?) {
                if (movieResponse != null) {
                    val entityList = movieResponse.results.toList().toResultEntityList()
                    MovieApplication.database.apply {
                        movieDao().insertMovie(entityList)
                    }
                }
            }

            override fun onError(t: Throwable?) {
                _isInProgress.postValue(true)
                Log.e("insertMovie()", "MovieResult erro: ${t?.message}")
                _isInError.postValue(true)
                _isInProgress.postValue(false)
            }

            override fun onComplete() {
                getMovieQuery()
            }

        }
    }

    private fun getMovieQuery(): Disposable{
        return MovieApplication.database.movieDao()
            .getMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {responseEntityList ->
                    _isInProgress.postValue(true)
                    if (responseEntityList != null && responseEntityList.isNotEmpty()){
                        _isInError.postValue(false)
                        _movies.postValue(responseEntityList.toResultList())
                    } else{
                        insertMovie()
                    }
                    _isInProgress.postValue(false)
                },
                {
                    _isInProgress.postValue(true)
                    Log.e("getMovieQuery()", "Database error: ${it.message}")
                    _isInError.postValue(true)
                    _isInProgress.postValue(false)
                }
            )
    }
    fun fethDataFromDatabase(): Disposable = getMovieQuery()

}