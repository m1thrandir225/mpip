package com.sebastijanzindl.labs3.domain

import com.sebastijanzindl.labs3.domain.Models.Movie

interface LocalMovieDataSource {
    suspend fun add(movie: Movie);
    suspend fun getAll(): List<Movie>;

    suspend fun getByID(id: String): Movie;
}