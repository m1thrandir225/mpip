package com.sebastijanzindl.labs3.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sebastijanzindl.labs3.domain.Models.Movie
import com.sebastijanzindl.labs3.domain.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondFragmentViewModel(private val repository: MovieRepository): ViewModel(){
    private val liveData = MutableLiveData<Movie>()

    fun getLiveData(): LiveData<Movie> = liveData

    fun listDetails(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(repository.getByID(movieId))
        }
    }
}