package com.example.googryandroidarchitecturestudy.ui.viewmodel

import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.RecentSearch

class MovieRecentViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {
    val recentList = ObservableField<List<RecentSearch>>(emptyList())

    val onChipClick: (String) -> Unit = {
        showMovieSearchEvent.set(it)
    }

    val showMovieSearchEvent = ObservableField("")
    val showSearchRecentFailedEvent = ObservableField<Exception>()

    suspend fun getRecentKeywords() {
        try {
            val keywords = repository.searchRecent()
            recentList.set(keywords)
        } catch (e: Exception) {
            showSearchRecentFailedEvent.set(e)
        }
    }

    fun showMovieSearchEventCompleted() {
        showMovieSearchEvent.set("")
    }
}