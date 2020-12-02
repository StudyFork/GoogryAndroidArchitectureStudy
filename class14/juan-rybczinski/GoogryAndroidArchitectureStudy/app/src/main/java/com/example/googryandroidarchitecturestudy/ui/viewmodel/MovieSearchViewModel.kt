package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.content.Context
import android.view.View
import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import java.util.*

class MovieSearchViewModel(
    context: Context
) : MovieViewModel(context) {
    val movieList = ObservableField<List<Movie>>(emptyList())
    val loading = ObservableField(View.GONE)

    val showQueryEmptyEvent = ObservableField<Unit>()
    val showNoSearchResultEvent = ObservableField<Unit>()

    val showSearchMovieFailedEvent = ObservableField<Exception>()
    val showSaveRecentFailedEvent = ObservableField<Exception>()

    val showRecentKeywordsEvent = ObservableField(false)

    suspend fun queryMovieList(query: String) {
        if (query.isEmpty()) {
            showQueryEmptyEvent.notifyChange()
            return
        }

        try {
            loading.set(View.VISIBLE)
            repository.insertRecent(
                RecentSearch(Date(System.currentTimeMillis()), query)
            )
        } catch (e: Exception) {
            showSaveRecentFailedEvent.notifyChange()
        } finally {
            try {
                val movies = repository.searchMovies(query)
                loading.set(View.GONE)
                if (movies.isEmpty()) {
                    showNoSearchResultEvent.notifyChange()
                    return
                }
                hideKeyboardEvent.notifyChange()
                movieList.set(movies)
            } catch (e: Exception) {
                showSearchMovieFailedEvent.notifyChange()
            }
        }
    }

    fun clickRecentButton() {
        showRecentKeywordsEvent.set(true)
    }

    fun clickRecentButtonCompleted() {
        showRecentKeywordsEvent.set(false)
    }
}