package com.example.architecturestudy.viewmodel

import androidx.databinding.ObservableField
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.data.repository.MovieRespositoryImpl

class MainViewModel {
    private val movieRepository = MovieRespositoryImpl()

    var failMsgObservableField = ObservableField<Void>()
    var movieListObservableField = ObservableField<List<MovieData>>()

    fun searchMovieOnRemote(title: String) {
        movieRepository.searchMovieOnRemote(
            title,
            success = { movieList -> movieListObservableField.set(movieList) },
            fail = { t -> failMsgObservableField.set(null) })
    }
}