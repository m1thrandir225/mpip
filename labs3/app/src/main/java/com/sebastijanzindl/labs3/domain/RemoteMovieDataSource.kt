package com.sebastijanzindl.labs3.domain

import com.sebastijanzindl.labs3.domain.Models.Movie

interface RemoteMovieDataSource {
    suspend fun search(query: String): Movie;
}