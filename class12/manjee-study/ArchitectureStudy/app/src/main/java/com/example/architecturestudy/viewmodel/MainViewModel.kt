package com.example.architecturestudy.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.repository.MovieRepository

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {

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