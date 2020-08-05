package com.hyper.hyapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl

class MainViewModel : ViewModel() {
    private val movieRepository = NaverRepositoryImpl(NaverRemoteDataSourceImpl())

    val movieName = MutableLiveData<String>()
    private val _message = MutableLiveData<Throwable>()
    private val _movieList = MutableLiveData<List<ResultGetSearchMovie.Item>>()

    val message: LiveData<Throwable> = _message
    val movieList: LiveData<List<ResultGetSearchMovie.Item>> = _movieList

    fun movieSearch() {
        val movieTitle = movieName.value.toString()
        if (movieTitle.isEmpty()) {
            MainActivity().showEmptyMessage()
        } else {
            movieRepository.movieSearch(
                movieTitle,
                success = { _movieList.value = it },
                failure = { _message.value = it })
        }
    }
}