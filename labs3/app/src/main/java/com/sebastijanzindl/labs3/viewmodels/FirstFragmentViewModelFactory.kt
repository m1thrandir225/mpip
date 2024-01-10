package com.sebastijanzindl.labs3.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sebastijanzindl.labs3.domain.MovieRepository
import com.sebastijanzindl.labs3.domain.Retrofit.ApiProvider
import com.sebastijanzindl.labs3.domain.Retrofit.RetrofitDataSource
import com.sebastijanzindl.labs3.domain.Room.Lab3Database
import com.sebastijanzindl.labs3.domain.Room.RoomDataSource

class FirstFragmentViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(MovieRepository::class.java)
            .newInstance(
                MovieRepository(
                    remoteSource = RetrofitDataSource(ApiProvider.getAPI()),
                    localSource = RoomDataSource(Lab3Database.getDB(context).dao())
                )
            )
    }
}