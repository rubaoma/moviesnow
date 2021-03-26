package com.rubdev.moviesnow.viewmodel

import androidx.lifecycle.ViewModel
import com.rubdev.moviesnow.di.DaggerAppComponent
import com.rubdev.moviesnow.repository.MoviesRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieViewModel: ViewModel() {

    @Inject
    lateinit var repository: MoviesRepository
    private val compositeDisposable by lazy { CompositeDisposable() }


    init {
        DaggerAppComponent.create().inject(this)
        compositeDisposable.add(repository.fetchDataFromDatabase())
    }

    /**
     * Método OnCLeared() que limpa todas chamadas Disposables quando o ViewModel for destruido pelo
     * ciclo de vida do Android
     * */

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}