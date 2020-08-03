package com.example.study.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.study.data.model.NaverApiData
import com.example.study.data.repository.MovieListRepositoryImpl

class MovieViewModel : ViewModel() {
    private val repository = MovieListRepositoryImpl()
    val movieList = ObservableField<List<NaverApiData.Item>>()
    val fail = ObservableField<Throwable>()
    val noQuery = ObservableField(0)
    val searchString = ObservableField<String>()


    fun requestMovieList() {
        if (searchString.get().toString().isNullOrEmpty()) {
            showQueryEmpty()
        } else {
            searchString.get()?.let { doSearch(it) }
        }
    }

    private fun showQueryEmpty() {
        noQuery.notifyChange()
    }

    private fun doSearch(query: String) {
        repository.doSearch(
            query = query,
            response = { movieList.set(it) },
            fail = { fail.set(it) })

    }
}
