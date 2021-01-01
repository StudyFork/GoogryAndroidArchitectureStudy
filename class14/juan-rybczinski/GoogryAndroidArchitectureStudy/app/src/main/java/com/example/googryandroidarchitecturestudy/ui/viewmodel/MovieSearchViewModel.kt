package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.view.View
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*

class MovieSearchViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    private val _movieList = MutableLiveData<List<Movie>>(emptyList())
    val movieList: LiveData<List<Movie>>
        get() = _movieList

    private val _loading = MutableLiveData(View.GONE)
    val loading: LiveData<Int>
        get() = _loading

    private val _showQueryEmptyEvent = MutableLiveData<Unit>()
    val showQueryEmptyEvent: LiveData<Unit>
        get() = _showQueryEmptyEvent

    private val _showNoSearchResultEvent = MutableLiveData<Unit>()
    val showNoSearchResultEvent: LiveData<Unit>
        get() = _showNoSearchResultEvent

    private val _showSearchMovieFailedEvent = MutableLiveData<Exception>()
    val showSearchMovieFailedEvent: LiveData<Exception>
        get() = _showSearchMovieFailedEvent

    private val _showSaveRecentFailedEvent = MutableLiveData<Exception>()
    val showSaveRecentFailedEvent: LiveData<Exception>
        get() = _showSaveRecentFailedEvent

    private val _showRecentKeywordsEvent = SingleLiveEvent<Unit>()
    val showRecentKeywordsEvent: LiveData<Unit>
        get() = _showRecentKeywordsEvent

    val query = MutableLiveData("")

    fun queryMovieList() {
        viewModelScope.launch {
            query.value?.let {
                if (it.isEmpty()) {
                    _showQueryEmptyEvent.value = Unit
                    return@launch
                }

                try {
                    _loading.value = View.VISIBLE
                    repository.insertRecent(
                        RecentSearch(Date(System.currentTimeMillis()), it)
                    )
                } catch (e: Exception) {
                    _showSaveRecentFailedEvent.value = e
                } finally {
                    try {
                        val movies = repository.searchMovies(it)
                        if (movies.isEmpty()) {
                            _showNoSearchResultEvent.value = Unit
                            return@launch
                        }
                        hideKeyboardEvent.value = Unit
                        _movieList.value = movies
                    } catch (e: Exception) {
                        _showSearchMovieFailedEvent.value = e
                    } finally {
                        _loading.value = View.GONE
                    }
                }
            }
        }
    }

    fun clickRecentButton() {
        _showRecentKeywordsEvent.call()
    }
}