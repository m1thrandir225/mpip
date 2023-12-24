package com.sebastijanzindl.lab2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.lab2.models.Movie
import com.sebastijanzindl.lab2.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailsViewModel (private val repository: MovieRepository): ViewModel() {
    private val details = MutableLiveData<Movie>();
    fun getDetails(): LiveData<Movie> = details;
    fun listDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            details.postValue(repository.getMovie(movieId));
        }
    }
}