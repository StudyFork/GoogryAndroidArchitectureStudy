package com.jay.aas.ui.movie

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.aas.base.BaseViewModel
import com.jay.aas.data.MovieRepository
import com.jay.aas.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel @ViewModelInject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _movieItems = MutableLiveData<List<Movie>>(emptyList())
    val movieItems: LiveData<List<Movie>> get() = _movieItems
    private val _movieDetailLink = MutableLiveData<String>()
    val movieDetailLink: LiveData<String> get() = _movieDetailLink
    val query = MutableLiveData<String>()

    private val _hideKeyboardEvent = MutableLiveData<Unit>()
    val hideKeyboardEvent: LiveData<Unit> get() = _hideKeyboardEvent
    private val _showSearchFailedEvent = MutableLiveData<Unit>()
    val showSearchFailedEvent: LiveData<Unit> get() = _showSearchFailedEvent
    private val _startSearchHistoryEvent = MutableLiveData<Unit>()
    val startSearchHistoryEvent: LiveData<Unit> get() = _startSearchHistoryEvent

    fun getMovies() {
        viewModelScope.launch {
            val movies = movieRepository.getMovies()
            _movieItems.value = movies
        }
    }

    fun searchMovies() {
        val movieQuery = query.value ?: return
        if (movieQuery.isEmpty()) return

        _hideKeyboardEvent.value = Unit
        viewModelScope.launch {
            try {
                showLoading()
                val movies = movieRepository.getSearchMovies(movieQuery)
                hideLoading()
                _movieItems.value = movies
            } catch (e: Exception) {
                e.printStackTrace()
                hideLoading()
                _showSearchFailedEvent.value = Unit
            }
        }
    }

    fun searchHistories() {
        _startSearchHistoryEvent.value = Unit
    }

    fun openMovieDetail(link: String) {
        _movieDetailLink.value = link
    }

}