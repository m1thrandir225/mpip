package com.sebastijanzindl.lab2.fakeapi

import com.sebastijanzindl.lab2.models.Movie

class FakeAPI {
    private val movies: MutableList<Movie> = ArrayList();

    fun getAllMovies(): MutableList<Movie> {
        return movies;
    }

    fun getSingleMovie(movieId: String): Movie {
        return  movies.find { it.id.toString() == movieId }!!
    }

    fun addMovie(newMovie: Movie): MutableList<Movie> {
        movies.add(newMovie);

        return movies;
    }
}