package com.jay.aas.ui.movie

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.jay.aas.base.BaseViewModel
import com.jay.aas.data.MovieRepository
import com.jay.aas.model.Movie
import kotlinx.coroutines.launch

class MovieViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val movieItems = ObservableField<List<Movie>>(emptyList())
    val movieDetailLink = ObservableField<String>()
    val query = ObservableField<String>()

    val hideKeyboardEvent = ObservableField<Unit>()
    val showSearchFailedEvent = ObservableField<Unit>()
    val startSearchHistoryEvent = ObservableField<Unit>()

    fun getMovies() {
        viewModelScope.launch {
            val movies = movieRepository.getMovies()
            movieItems.set(movies)
        }
    }

    fun searchMovies() {
        val movieQuery = query.get() ?: return
        if (movieQuery.isEmpty()) return

        hideKeyboardEvent.notifyChange()
        viewModelScope.launch {
            try {
                showLoading()
                val movies = movieRepository.getSearchMovies(movieQuery)
                hideLoading()
                movieItems.set(movies)
            } catch (e: Exception) {
                e.printStackTrace()
                hideLoading()
                showSearchFailedEvent.notifyChange()
            }
        }
    }

    fun searchHistories() {
        startSearchHistoryEvent.notifyChange()
    }

    fun openMovieDetail(link: String) {
        movieDetailLink.set(link)
    }

}