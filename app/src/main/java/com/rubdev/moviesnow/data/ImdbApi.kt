package com.rubdev.moviesnow.data

import com.rubdev.moviesnow.model.MovieResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface ImdbApi {

    @GET("now_playing")
//    @GET("now_playing?api_key=&language=pt-BR&page=")

    fun getMoviesNowPlaying(
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: String
    ): Flowable<MovieResponse>
}