package com.rubdev.moviesnow.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rubdev.moviesnow.model.Dates
import com.rubdev.moviesnow.model.Result

@Entity(tableName = "moviesRaw")
data class MovieEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,

    @ColumnInfo(name = "dates")
    val dates: Dates,

    @ColumnInfo(name = "page")
    val page: Int,

    @ColumnInfo(name = "resultsMovies")
    val results: List<Result>,

    @ColumnInfo(name = "totalPages")
    val total_pages: Int,

    @ColumnInfo(name = "totalResults")
    val total_results: Int
)
