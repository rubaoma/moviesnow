package com.rubdev.moviesnow.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Single

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie:List<ResultEntity>)

    @Query("SELECT * FROM resultMovie")
    fun getMovies(): Single<List<ResultEntity>>
}