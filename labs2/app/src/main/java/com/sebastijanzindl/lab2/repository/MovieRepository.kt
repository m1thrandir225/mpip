package com.sebastijanzindl.lab2.repository

import com.sebastijanzindl.lab2.fakeapi.FakeAPI
import com.sebastijanzindl.lab2.models.Movie
import java.util.UUID

class MovieRepository (val api: FakeAPI) {
    fun listMovies(): MutableList<Movie> {
        return api.getAllMovies();
    }
    fun getMovie(movieId: String): Movie {
        return api.getSingleMovie(movieId);
    }

    fun addNewMovie(title: String, description: String, director: String ,actors: List<String>): MutableList<Movie> {

        val newMovie = Movie(UUID.randomUUID(), title, description, director, actors);

        return api.addMovie(newMovie);
    }
}