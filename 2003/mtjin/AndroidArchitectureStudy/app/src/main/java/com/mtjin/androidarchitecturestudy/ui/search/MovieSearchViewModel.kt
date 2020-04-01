package com.mtjin.androidarchitecturestudy.ui.search

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.mtjin.androidarchitecturestudy.data.search.Movie
import com.mtjin.androidarchitecturestudy.data.search.source.MovieRepository
import retrofit2.HttpException

class MovieSearchViewModel(private val movieRepository: MovieRepository) {
    var query: ObservableField<String> = ObservableField("")
    var movieList: ObservableField<ArrayList<Movie>> = ObservableField()
    var toastMsg: ObservableField<String> = ObservableField()
    var lastPageMsg: ObservableBoolean = ObservableBoolean(false)
    var emptyQueryMsg: ObservableBoolean = ObservableBoolean(false)
    var noResultMsg: ObservableBoolean = ObservableBoolean(false)
    var networkErrorMsg: ObservableBoolean = ObservableBoolean(false)
    var successMsg: ObservableBoolean = ObservableBoolean(false)
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var scrollRestateState: ObservableBoolean = ObservableBoolean(false)
    var isLoadMore: Boolean = false

    var currentQuery: String = ""
    fun requestMovie() {
        isLoadMore = false
        currentQuery = query.get().toString()
        if (currentQuery.isEmpty()) {
            emptyQueryMsg.notifyChange()
        } else {
            isLoading.set(true)
            scrollRestateState.set(true)
            movieRepository.getSearchMovies(query.get().toString(),
                success = {
                    if (it.isEmpty()) {
                        noResultMsg.notifyChange()
                    } else {
                        movieList.get()?.clear()
                        movieList.set(it as ArrayList<Movie>?)
                        successMsg.notifyChange()
                    }
                    isLoading.set(false)
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> networkErrorMsg.notifyChange()
                        else -> toastMsg.set(it.message.toString())
                    }
                    isLoading.set(false)
                })
        }
    }

    fun requestPagingMovie(offset: Int) {
        isLoadMore = true
        isLoading.set(true)
        movieRepository.getPagingMovies(currentQuery, offset,
            success = {
                if (it.isEmpty()) {
                    lastPageMsg.notifyChange()
                } else {
                    movieList.set(it as ArrayList<Movie>?)
                    successMsg.notifyChange()
                }
                isLoading.set(false)
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> networkErrorMsg.notifyChange()
                    else -> toastMsg.set(it.message.toString())
                }
                isLoading.set(false)
            })
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}