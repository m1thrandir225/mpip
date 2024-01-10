package com.sebastijanzindl.labs3.domain.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sebastijanzindl.labs3.domain.Models.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: Movie)
    @Query("SELECT * FROM movie")
    suspend fun getAll(): List<Movie>
    @Query("SELECT * FROM movie WHERE imdbID=:id LIMIT 1")
    suspend fun getByID(id: String): Movie;
}