package com.sebastijanzindl.lab2.fakeapi

import com.sebastijanzindl.lab2.models.Movie
import java.util.UUID

class FakeAPIProvider {
    companion object {
        @Volatile
        private var apiInstance: FakeAPI? = null;

        @JvmStatic
        fun getInstance() = apiInstance ?: synchronized(this)  {
            apiInstance ?: createApi().also { apiInstance = it }
        }

        private fun createApi(): FakeAPI {
            val api = FakeAPI();
            api.addMovie(Movie(UUID.randomUUID(), "The Shawshank Redemptgion", "Over the course of several years, two convicts form a friendship, seeking consolation and, eventually, redemption through basic compassion.", "Frank Darabont", listOf("Tim Robbins", "Morgan Freeman", "Bob Gunton")));
            api.addMovie(Movie(UUID.randomUUID(), "The Godfather", "Don Vito Corleone, head of a mafia family, decides to hand over his empire to his youngest son Michael. However, his decision unintentionally puts the lives of his loved ones in grave danger.", "Francis Ford Coppola", listOf("Marlon Brando", "Al Pacino", "James Caan")));
            api.addMovie(Movie(UUID.randomUUID(), "The Dark Knight", "When the menace known as the Joker wreaks havoc and chaos on the people of Gotham, Batman must accept one of the greatest psychological and physical tests of his ability to fight injustice", "Christopher Nolabn", listOf("Christian Bale", "Heath Ledger", "Aaron Eckhart")));
            api.addMovie(Movie(UUID.randomUUID(), "12 Angry Men", "The jury in a New York City murder trial is frustrated by a single member whose skeptical caution forces them to more carefully consider the evidence before jumping to a hasty verdict.", "Sidney Lumet", listOf("Henry Fonda", "Lee J. Cobb", "Martin Balsam")));

            return api;
        }
    }
}