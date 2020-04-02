package com.mtjin.androidarchitecturestudy.ui.search

import android.util.Log
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.mtjin.androidarchitecturestudy.data.search.Movie
import com.mtjin.androidarchitecturestudy.data.search.source.MovieRepository
import retrofit2.HttpException

class MovieSearchViewModel(private val movieRepository: MovieRepository) {
    enum class MessageSet {
        BASIC,
        LAST_PAGE,
        EMPTY_QUERY,
        NETWORK_ERROR,
        SUCCESS,
        NO_RESULT
    }

    var query: ObservableField<String> = ObservableField("")
    var movieList: ObservableField<List<Movie>> = ObservableField()
    var toastMsg: ObservableField<MessageSet> = ObservableField()
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var isLoadMore: Boolean = false
    var currentQuery: String = ""

    fun requestMovie() {
        isLoadMore = false
        currentQuery = query.get().toString()
        if (currentQuery.isEmpty()) {
            toastMsg.set(MessageSet.EMPTY_QUERY)
            //emptyQueryMsg.notifyChange()
        } else {
            isLoading.set(true)
            movieRepository.getSearchMovies(query.get().toString(),
                success = {
                    if (it.isEmpty()) {
                        toastMsg.set(MessageSet.NO_RESULT)
                    } else {
                        movieList.set(it)
                        toastMsg.set(MessageSet.SUCCESS)
                    }
                    isLoading.set(false)
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> toastMsg.set(MessageSet.NETWORK_ERROR)
                        else -> toastMsg.set(MessageSet.LAST_PAGE)
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
                    toastMsg.set(MessageSet.LAST_PAGE)
                } else {
                    movieList.set(it)
                    toastMsg.set(MessageSet.SUCCESS)
                }
                isLoading.set(false)
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> toastMsg.set(MessageSet.NETWORK_ERROR)
                    else -> toastMsg.set(MessageSet.LAST_PAGE)
                }
                isLoading.set(false)
            })
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}