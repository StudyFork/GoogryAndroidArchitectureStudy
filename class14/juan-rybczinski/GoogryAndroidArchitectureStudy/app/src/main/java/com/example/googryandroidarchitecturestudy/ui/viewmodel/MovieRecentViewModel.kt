package com.example.googryandroidarchitecturestudy.ui.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MovieRecentViewModel(
    private val repository: MovieRepository
) : BaseViewModel() {
    init {
        viewModelScope.launch {
            getRecentKeywords()
        }
    }

    val recentList = ObservableField<List<RecentSearch>>(emptyList())

    val onChipClick: (String) -> Unit = {
        _showMovieSearchEvent.value = it
    }

    private val _showMovieSearchEvent = SingleLiveEvent<String>()
    val showMovieSearchEvent: LiveData<String>
        get() = _showMovieSearchEvent

    val showSearchRecentFailedEvent = ObservableField<Exception>()

    suspend fun getRecentKeywords() {
        try {
            val keywords = repository.searchRecent()
            recentList.set(keywords)
        } catch (e: Exception) {
            showSearchRecentFailedEvent.set(e)
        }
    }
}