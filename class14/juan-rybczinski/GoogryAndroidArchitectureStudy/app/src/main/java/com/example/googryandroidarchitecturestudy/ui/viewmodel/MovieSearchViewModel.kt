package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.view.View
import androidx.databinding.Bindable
import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.BR
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
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

    val showRecentKeywordsEvent = ObservableField(false)

    val query = ObservableField("")

    suspend fun queryMovieList() {
        query.get()?.let {
            if (it.isEmpty()) {
                showQueryEmptyEvent.notifyChange()
                return
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
                        return
                    }
                    hideKeyboardEvent.notifyChange()
                    movieList.set(movies)
                } catch (e: Exception) {
                    showSearchMovieFailedEvent.notifyChange()
                }
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