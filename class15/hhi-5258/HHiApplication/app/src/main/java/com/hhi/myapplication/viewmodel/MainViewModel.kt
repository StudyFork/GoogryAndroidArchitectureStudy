package com.hhi.myapplication.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableField
import com.hhi.myapplication.base.BaseViewModel
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class MainViewModel : BaseViewModel(){
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

            loading.set(View.VISIBLE)
            repositoryDataSourceImpl.searchMovies(query.get()!!,
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