package com.sebastijanzindl.labs3.domain.Retrofit

import com.sebastijanzindl.labs3.domain.Models.Movie
import com.sebastijanzindl.labs3.domain.RemoteMovieDataSource
import java.lang.Exception

class RetrofitDataSource(private val movieApi: API): RemoteMovieDataSource {
    override suspend fun search(query: String): Movie {
        val response = movieApi.search(query)
        val body = response.body()

        if(response.isSuccessful && body != null) {
            return body
        }

        throw Exception(response.message())
    }
}