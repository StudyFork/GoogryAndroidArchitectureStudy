package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.util.SingleLiveEvent
import kotlinx.coroutines.launch
import java.util.*

class MovieSearchViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {
    val movieList = ObservableField<List<Movie>>(emptyList())
    val loading = ObservableField(View.GONE)

    val showQueryEmptyEvent = ObservableField<Unit>()
    val showNoSearchResultEvent = ObservableField<Unit>()

    val showSearchMovieFailedEvent = ObservableField<Exception>()
    val showSaveRecentFailedEvent = ObservableField<Exception>()

    private val _showRecentKeywordsEvent = SingleLiveEvent<Unit>()
    val showRecentKeywordsEvent: LiveData<Unit>
        get() = _showRecentKeywordsEvent

    val query = ObservableField("")

    fun queryMovieList() {
        viewModelScope.launch {
            query.get()?.let {
                if (it.isEmpty()) {
                    showQueryEmptyEvent.notifyChange()
                    return@launch
                }

                try {
                    loading.set(View.VISIBLE)
                    repository.insertRecent(
                        RecentSearch(Date(System.currentTimeMillis()), it)
                    )
                } catch (e: Exception) {
                    showSaveRecentFailedEvent.notifyChange()
                } finally {
                    try {
                        val movies = repository.searchMovies(it)
                        loading.set(View.GONE)
                        if (movies.isEmpty()) {
                            showNoSearchResultEvent.notifyChange()
                            return@launch
                        }
                        hideKeyboardEvent.notifyChange()
                        movieList.set(movies)
                    } catch (e: Exception) {
                        showSearchMovieFailedEvent.notifyChange()
                    }
                }
            }
        }
    }

    fun clickRecentButton() {
        _showRecentKeywordsEvent.call()
    }
}