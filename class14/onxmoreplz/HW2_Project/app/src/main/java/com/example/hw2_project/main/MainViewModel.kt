package com.example.hw2_project.main

import androidx.databinding.ObservableField
import com.example.hw2_project.data.repository.MovieRepositoryImpl

class MainViewModel(private val movieRepository: MovieRepositoryImpl) {
    val queryObservableField = ObservableField<String>()
    val movieListTest: ObservableField<List<com.example.hw2_project.data.api.NaverMovieData.NaverMovie>> = ObservableField()

    val isEmptyQuery = ObservableField<Unit>()
    val successToGetMovie = ObservableField<Unit>()
    val failToGetMovie = ObservableField<Unit>()
    val startRecentActivity = ObservableField<Unit>()

    fun getMovieFromRepository() {
        val query = queryObservableField?.get().toString()
        if (query.isEmpty()) {
            isEmptyQuery.notifyChange()
        } else {
            movieRepository.getMovieList(
                query,
                success = {
                    it.items.run {
                        movieListTest.set(it.items)
                        successToGetMovie.notifyChange()
                    }
                },
                fail = {
                    failToGetMovie.notifyChange()
                }
            )
        }
    }

    fun clickRecentBtn() {
        startRecentActivity.notifyChange()
    }

}