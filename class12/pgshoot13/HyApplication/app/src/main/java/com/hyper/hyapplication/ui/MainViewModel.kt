package com.hyper.hyapplication.ui

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.hyper.hyapplication.model.ResultGetSearchMovie
import com.hyper.hyapplication.repository.NaverRepositoryImpl
import com.hyper.hyapplication.source.remote.NaverRemoteDataSourceImpl

class MainViewModel {
    private val movieList = NaverRepositoryImpl(NaverRemoteDataSourceImpl())

    val movieName = ObservableField<String>()
    val message = ObservableField<Throwable>()
    val movieItem = ObservableArrayList<ResultGetSearchMovie.Item>()

    fun movieSearch(query: String) {
        if (query.isEmpty()) {
            MainActivity().showEmptyMessage()
        } else {
            movieList.movieSearch(
                query,
                success = {
                    movieItem.clear()
                    movieItem.addAll(it)
                },
                failure = { message.set(it) })
        }
    }
}