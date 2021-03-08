package com.rubdev.moviesnow

import android.app.Application
import com.rubdev.moviesnow.data.database.MoviesDatabase

class MovieApplication: Application() {

    companion object{
        lateinit var instance: MovieApplication
        lateinit var database: MoviesDatabase
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        database = MoviesDatabase.invoke(this)
    }
}