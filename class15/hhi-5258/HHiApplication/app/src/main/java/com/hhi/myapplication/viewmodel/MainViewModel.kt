package com.hhi.myapplication.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainViewModel {
    private val repositoryDataSourceImpl = NaverRepositoryDataSourceImpl()
    val movieList = ObservableField<ArrayList<MovieData.MovieItem>>()
    val emptyQueryEvent = ObservableField<Unit>()
    val hideKeyBoardEvent = ObservableField<Unit>()
    val searchRecentQueryEvent = ObservableField<Unit>()
    val loading = ObservableField(View.GONE)

    fun searchMovie(query: String) {
        hideKeyBoardEvent.notifyChange()
        if (query.isNullOrBlank()) {
            emptyQueryEvent.notifyChange()
        } else {
            repositoryDataSourceImpl.saveQuery(query)

            loading.set(View.VISIBLE)
            repositoryDataSourceImpl.searchMovies(query,
                success = {
                    movieList.set(it.items)
                    loading.set(View.GONE)
                },
                failed = {
                    Log.e("search_failed", it.toString())
                    loading.set(View.GONE)
                }
            )
        }
    }

    fun searchRecentQuery() {
        searchRecentQueryEvent.notifyChange()
    }
}