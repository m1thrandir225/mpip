package com.sebastijanzindl.lab2.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.lab2.models.Movie
import com.sebastijanzindl.lab2.repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel (private val repository: MovieRepository): ViewModel() {

    private val liveData = MutableLiveData<List<Movie>>();
    fun getLiveDataMovies(): LiveData<List<Movie>> = liveData;
    fun listAllMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            val movies = repository.listMovies();

            liveData.postValue(movies);
        }
    }
}