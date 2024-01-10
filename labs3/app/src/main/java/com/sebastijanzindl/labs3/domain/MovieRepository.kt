package com.sebastijanzindl.labs3.domain

import com.sebastijanzindl.labs3.domain.Models.Movie

class MovieRepository(private val remoteSource: RemoteMovieDataSource, private val localSource: LocalMovieDataSource){
    suspend fun search(q: String): Movie {
        return remoteSource.search(q).apply {
            localSource.add(this)
        }
    }

    suspend fun new(movie: Movie) {
        return localSource.add(movie = movie);
    }

    suspend fun allMovies(): List<Movie> {
        return localSource.getAll();
    }

    suspend fun getByID(id: String): Movie {
        return localSource.getByID(id);
    }
}