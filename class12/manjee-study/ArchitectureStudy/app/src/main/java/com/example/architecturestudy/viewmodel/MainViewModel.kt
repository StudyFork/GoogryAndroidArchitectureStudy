package com.example.architecturestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.repository.MovieRespositoryImpl

class MainViewModel : ViewModel() {
    private val movieRepository = MovieRespositoryImpl()

    var failMsgLiveData = MutableLiveData<Throwable>()
    var movieListLiveData = MutableLiveData<List<MovieData>>()
    var movieTitleLiveData = MutableLiveData<String>()

    fun searchMovieOnRemote() {
        movieRepository.searchMovieOnRemote(
            movieTitleLiveData.value.toString(),
            success = { movieList -> movieListLiveData.value = movieList },
            fail = { t -> failMsgLiveData.value = t })
    }
}