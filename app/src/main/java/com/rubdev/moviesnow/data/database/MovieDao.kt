package com.rubdev.moviesnow.data.database

import androidx.room.Dao
import androidx.room.Query

@Dao
interface MovieDao {

    @Query("SELECT * FROM moviesRaw")
    fun getMovies(): List<MovieEntity>
}