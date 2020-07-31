package com.example.architecturestudy.viewmodel

import androidx.databinding.ObservableField
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.repository.MovieRespositoryImpl

class MainViewModel {
    private val movieRepository = MovieRespositoryImpl()

    var failMsgObservableField = ObservableField<Throwable>()
    var movieListObservableField = ObservableField<List<MovieData>>()
    var movieTitleObservableField = ObservableField<String>()

    fun searchMovieOnRemote() {
        movieRepository.searchMovieOnRemote(
            movieTitleObservableField.get().toString(),
            success = { movieList -> movieListObservableField.set(movieList) },
            fail = { t -> failMsgObservableField.set(t) })
    }
}