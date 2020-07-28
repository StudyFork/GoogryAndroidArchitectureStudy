package com.example.study.viewmodel

import androidx.databinding.ObservableField
import com.example.study.data.model.NaverApiData
import com.example.study.data.repository.MovieListRepositoryImpl

class MovieViewModel {
    private val repository = MovieListRepositoryImpl()
    val movieList = ObservableField<List<NaverApiData.Item>>()
    val fail = ObservableField<Throwable>()
    val noQuery = ObservableField(0)

    fun requestMovieList(query: String) {
        if (query.isEmpty()) {
            showQueryEmpty()
        } else {
            doSearch(query)
        }
    }

    private fun showQueryEmpty() {
        noQuery.set(noQuery.get()?.plus(1))
    }
    private fun doSearch(query: String) {
        repository.doSearch(
            query = query,
            response = {movieList.set(it)},
            fail = {fail.set(it)})
    }
}
