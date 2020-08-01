package com.hyper.hyapplication.ui

import androidx.databinding.ObservableField
import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl

class MainViewModel {
    private val movieRepository = NaverRepositoryImpl(NaverRemoteDataSourceImpl())

    val movieName = ObservableField<String>()
    val message = ObservableField<Throwable>()
    val movieList = ObservableField<List<ResultGetSearchMovie.Item>>()

    fun movieSearch() {
        if (movieName.get().toString().isEmpty()) {
            MainActivity().showEmptyMessage()
        } else {
            movieRepository.movieSearch(
                movieName.get().toString(),
                success = {
                    movieList.set(it)
                },
                failure = { message.set(it) })
        }
    }
}