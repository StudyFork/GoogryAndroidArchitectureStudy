package com.hhi.myapplication.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.hhi.myapplication.base.BaseViewModel
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainViewModel : BaseViewModel(), viewModel() {
    private val repositoryDataSourceImpl = NaverRepositoryDataSourceImpl()
    val movieList = ObservableField<ArrayList<MovieData.MovieItem>>()
    val emptyQueryEvent = ObservableField<Unit>()
    val hideKeyBoardEvent = ObservableField<Unit>()
    val searchRecentQueryEvent = ObservableField<Unit>()
    val query = ObservableField<String>()

    fun searchMovie() {
        hideKeyBoardEvent.notifyChange()
        if (query.get().isNullOrBlank()) {
            emptyQueryEvent.notifyChange()
        } else {
            repositoryDataSourceImpl.saveQuery(query.get()!!)

            visible.set(true)
            repositoryDataSourceImpl.searchMovies(query.get()!!,
                success = {
                    movieList.set(it.items)
                    visible.set(false)
                },
                failed = {
                    Log.e("search_failed", it.toString())
                    visible.set(false)
                }
            )
        }
    }

    fun searchRecentQuery() {
        searchRecentQueryEvent.notifyChange()
    }
}