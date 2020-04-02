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

    //    var toastMsg: ObservableField<String> = ObservableField()
    var toastMsg: ObservableField<MessageSet> = ObservableField()

    //    var lastPageMsg: ObservableBoolean = ObservableBoolean(false)
//    var emptyQueryMsg: ObservableBoolean = ObservableBoolean(false)
//    var noResultMsg: ObservableBoolean = ObservableBoolean(false)
//    var networkErrorMsg: ObservableBoolean = ObservableBoolean(false)
//    var successMsg: ObservableBoolean = ObservableBoolean(false)
    var isLoading: ObservableBoolean = ObservableBoolean(false)
    var scrollRestateState: ObservableBoolean = ObservableBoolean(false)
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
            scrollRestateState.set(true)
            movieRepository.getSearchMovies(query.get().toString(),
                success = {
                    if (it.isEmpty()) {
                        toastMsg.set(MessageSet.NO_RESULT)
                        //noResultMsg.notifyChange()
                    } else {
                        movieList.set(it)
                        toastMsg.set(MessageSet.SUCCESS)
                        //successMsg.notifyChange()
                    }
                    isLoading.set(false)
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> toastMsg.set(MessageSet.NETWORK_ERROR)//networkErrorMsg.notifyChange()
                        else -> toastMsg.set(MessageSet.LAST_PAGE)//toastMsg.set(it.message.toString())
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
                    //lastPageMsg.notifyChange()
                } else {
                    movieList.set(it)
                    toastMsg.set(MessageSet.SUCCESS)
                    //successMsg.notifyChange()
                }
                isLoading.set(false)
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> toastMsg.set(MessageSet.NETWORK_ERROR)//networkErrorMsg.notifyChange()
                    else -> toastMsg.set(MessageSet.LAST_PAGE)//toastMsg.set(it.message.toString())
                }
                isLoading.set(false)
            })
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}