package com.rubdev.moviesnow.data.database

import com.rubdev.moviesnow.model.Dates
import com.rubdev.moviesnow.model.GenreIds
import com.rubdev.moviesnow.model.MovieResponse
import com.rubdev.moviesnow.model.Result


fun ResultEntity.toResult() = Result(
   this.adult,
   this.backdrop_path,
   this.genre_ids,
   this.id,
   this.original_language,
   this.original_title,
   this.overview,
   this.popularity,
   this.poster_path,
   this.release_date,
   this.title,
   this.video,
   this.vote_average,
   this.vote_count
)

fun List<ResultEntity>.toResultList() = this.map { it.toResult() }

fun Result.toResultEntity() = ResultEntity(

    adult = this.adult,
    backdrop_path = this.backdrop_path,
    genre_ids = this.genre_ids,
    id = this.id,
    original_language = this.original_language,
    original_title = this.original_title,
    overview = this.overview,
    popularity = this.popularity,
    poster_path = this.poster_path,
    release_date = this.release_date,
    title = this.title,
    video = this.video,
    vote_average = this.vote_average,
    vote_count = this.vote_count
)

fun List<Result>.toResultEntityList() = this.map { it.toResultEntity() }

//
fun GenreIdsEntity.toGenreIds() = GenreIds(
    this.num
)

fun List<GenreIdsEntity>.toGenreIdsList() = this.map { it.toGenreIds() }

fun GenreIds.toGenreIdsEntity() = GenreIdsEntity(
    num = this.num

)

fun List<GenreIds>.toGenreIdsEntityList() = this.map { it.toGenreIdsEntity() }


//fun MovieEntity.toMovieResponse() = MovieResponse(
//    dates = Dates(this.dates.maximum, this.dates.minimum),
//    page = this.page,
//    results = this.results,
//    total_pages = this.total_pages,
//    total_results = this.total_results
//)
//
//fun List<MovieEntity>.toMovieResponseList() = this.map { it.toMovieResponse() }
//
//fun MovieResponse.toMovieEntity() = MovieEntity(
//
//    dates = this.dates,
//    page = this.page,
//    results = this.results,
//    total_pages = this.total_pages,
//    total_results = this.total_results
//
//)
//
//fun List<MovieResponse>.toMovieEntityList() = this.map { it.toMovieEntity() }
//
//
//
//
