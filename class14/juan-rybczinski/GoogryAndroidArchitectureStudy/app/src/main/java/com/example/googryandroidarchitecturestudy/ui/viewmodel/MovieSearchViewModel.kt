package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import java.lang.Exception
import java.util.*

class MovieSearchViewModel(
    context: Context
) : MovieViewModel(context) {
    val movieList = ObservableField<List<Movie>>()
    val loading = ObservableField(false)

    val showQueryEmptyEvent = ObservableField<Unit>()
    val showNoSearchResultEvent = ObservableField<Unit>()
    val hideKeyboardEvent = ObservableField<Unit>()
    val showSaveRecentFailedEvent = ObservableField<Unit>()
    val showSearchMovieFailedEvent = ObservableField<Unit>()

    suspend fun queryMovieList(query: String) {
        if (query.isEmpty()) {
            showQueryEmptyEvent.notifyChange()
            return
        }

        try {
            loading.set(true)
            repository.insertRecent(
                RecentSearch(Date(System.currentTimeMillis()), query)
            )
        } catch (e: Exception) {
            showSaveRecentFailedEvent.notifyChange()
        } finally {
            try {
                val movies = repository.searchMovies(query)
                loading.set(false)
                if (movies.isEmpty()) {
                    showNoSearchResultEvent.notifyChange()
                    return
                }
                hideKeyboardEvent.notifyChange()
                movieList.set(movies)
                // TODO: 2020/11/30 Check same movies case 
            } catch (e: Exception) {
                showSearchMovieFailedEvent.notifyChange()
            }
        }
    }
}