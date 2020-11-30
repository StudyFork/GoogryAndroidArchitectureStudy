package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.content.Context
import androidx.databinding.ObservableField
import androidx.fragment.app.Fragment
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import java.lang.Exception

class MovieRecentViewModel(
    context: Context
) : MovieViewModel(context) {
    val recentList = ObservableField<List<RecentSearch>>()

    val showSearchRecentFailedEvent = ObservableField<Unit>()

    suspend fun getRecentKeywords() {
        try {
            val keywords = repository.searchRecent()
            recentList.set(keywords)
        } catch (e: Exception) {
            showSearchRecentFailedEvent.notifyChange()
        }
    }
}