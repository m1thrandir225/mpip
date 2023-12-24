package com.sebastijanzindl.lab2.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sebastijanzindl.lab2.fakeapi.FakeAPIProvider
import com.sebastijanzindl.lab2.repository.MovieRepository

class MovieListViewModelFactory (private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java).newInstance(
            MovieRepository(FakeAPIProvider.getInstance())
        )
    }
}