package com.sebastijanzindl.labs3.domain.Models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class Movie(
    @PrimaryKey val  imdbID: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Country") val country: String,
)