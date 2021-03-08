package com.rubdev.moviesnow.data.database

import com.rubdev.moviesnow.model.Dates
import com.rubdev.moviesnow.model.MovieResponse
import com.rubdev.moviesnow.model.Result

fun MovieEntity.toMovieResponse() = MovieResponse(
    Dates(this.dates.maximum, this.dates.minimum),
    this.page,
    this.results,
    this.total_pages,
    this.total_results
)

fun List<MovieEntity>.toMovieResponseList() = this.map { it.toMovieResponse() }

fun MovieResponse.toMovieEntity() = MovieEntity(

    dates = this.dates,
    page = this.page,
    results = this.results,
    total_pages = this.total_pages,
    total_results = this.total_results

)

fun List<MovieResponse>.toMovieEntityList() = this.map { it.toMovieEntity() }




