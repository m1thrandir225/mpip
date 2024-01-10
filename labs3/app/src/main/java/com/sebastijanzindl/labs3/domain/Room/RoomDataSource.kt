package com.sebastijanzindl.labs3.domain.Room

import com.sebastijanzindl.labs3.domain.LocalMovieDataSource
import com.sebastijanzindl.labs3.domain.Models.Movie

class RoomDataSource(private val dao: MovieDao): LocalMovieDataSource {
    override suspend fun add(movie: Movie) {
        return dao.add(movie);
    }

    override suspend fun getAll(): List<Movie> {
        return dao.getAll();
    }

    override suspend fun getByID(id: String): Movie {
        return dao.getByID(id);
    }
}