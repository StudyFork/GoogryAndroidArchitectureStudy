package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import com.example.googryandroidarchitecturestudy.domain.RecentSearch

class MovieRecentViewModel(
    context: Context
) : MovieViewModel(context) {
    val recentList = ObservableField<List<RecentSearch>>(emptyList())

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

    companion object {
        val showMovieSearchEvent = ObservableField("")
    }
}