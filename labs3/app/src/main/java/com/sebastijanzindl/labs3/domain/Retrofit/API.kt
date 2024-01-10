package com.sebastijanzindl.labs3.domain.Retrofit

import com.sebastijanzindl.labs3.domain.Models.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("/")
    suspend fun search(@Query("t") t: String): Response<Movie>
}