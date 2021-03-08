package com.rubdev.moviesnow.model

import com.squareup.moshi.Json


data class MovieResponse(
   @field:Json(name = "dates") val dates: Dates,
   @field:Json(name = "page") val page: Int,
   @field:Json(name = "results") val results: List<Result>,
   @field:Json(name = "total_pages") val total_pages: Int,
   @field:Json(name = "total_results") val total_results: Int
)