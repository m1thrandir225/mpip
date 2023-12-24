package com.sebastijanzindl.lab2.models

import java.util.UUID

data class Movie(
    val id: UUID,
    val title: String,
    val description: String,
    val director: String,
    val actors: List<String>
)
