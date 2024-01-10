package com.sebastijanzindl.labs3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.labs3.domain.Models.Movie
import com.sebastijanzindl.labs3.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FirstFragmentViewModel(private val repository: MovieRepository): ViewModel() {
    private val liveData = MutableLiveData<List<Movie>>();

    fun getLiveData(): LiveData<List<Movie>> = liveData

    fun search(q: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val movie = repository.search(q);
            repository.new(movie);
            liveData.postValue(repository.allMovies())
        }
    }

    fun listAll() {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(repository.allMovies());
        }
    }
}
