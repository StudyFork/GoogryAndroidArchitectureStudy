package com.mtjin.androidarchitecturestudy.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mtjin.data.model.search.Movie
import com.mtjin.data.source.search.MovieRepository
import retrofit2.HttpException

class MovieSearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private var currentQuery: String = ""
    val query = MutableLiveData<String>()
    private val _movieList = MutableLiveData<ArrayList<Movie>>()
    private val _toastMsg = MutableLiveData<MessageSet>()
    private val _isLoading = MutableLiveData<Boolean>(false)

    val movieList: LiveData<ArrayList<Movie>> get() = _movieList
    val toastMsg: LiveData<MessageSet> get() = _toastMsg
    val isLoading: LiveData<Boolean> get() = _isLoading


    fun requestMovie() {
        currentQuery = query.value.toString().trim()
        Log.d(TAG, currentQuery)
        if (currentQuery.isEmpty()) {
            _toastMsg.value = MessageSet.EMPTY_QUERY
        } else {
            _isLoading.value = true
            movieRepository.getSearchMovies(currentQuery,
                success = {
                    if (it.isEmpty()) {
                        _toastMsg.value = MessageSet.NO_RESULT
                    } else {
                        _movieList.value = it as ArrayList<Movie>
                        _toastMsg.value = MessageSet.SUCCESS
                    }
                    _isLoading.value = false
                },
                fail = {
                    Log.d(TAG, it.toString())
                    when (it) {
                        is HttpException -> _toastMsg.value = MessageSet.NETWORK_ERROR
                        else -> _toastMsg.value = MessageSet.NETWORK_ERROR
                    }
                    _isLoading.value = false
                })
        }
    }

    fun requestPagingMovie(offset: Int) {
        Log.d(TAG, currentQuery)
        _isLoading.value = true
        movieRepository.getPagingMovies(currentQuery, offset,
            success = {
                if (it.isEmpty()) {
                    _toastMsg.value = MessageSet.LAST_PAGE
                } else {
                    val pagingMovieList = _movieList.value
                    pagingMovieList?.addAll(it)
                    _movieList.value = pagingMovieList
                    _toastMsg.value = MessageSet.SUCCESS
                }
                _isLoading.value = false
            },
            fail = {
                Log.d(TAG, it.toString())
                when (it) {
                    is HttpException -> MessageSet.NETWORK_ERROR
                    else -> _toastMsg.value = MessageSet.LAST_PAGE
                }
                _isLoading.value = false
            })
    }

    enum class MessageSet {
        LAST_PAGE,
        EMPTY_QUERY,
        NETWORK_ERROR,
        SUCCESS,
        NO_RESULT
    }

    companion object {
        const val TAG = "MovieSearchTAG"
    }
}