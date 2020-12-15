package com.example.googryandroidarchitecturestudy.ui.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.domain.RecentSearch
import com.example.googryandroidarchitecturestudy.util.SingleLiveEvent
import kotlinx.coroutines.launch

class MovieRecentViewModel @ViewModelInject constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {
    init {
        viewModelScope.launch {
            getRecentKeywords()
        }
    }

    private val _recentList = MutableLiveData<List<RecentSearch>>(emptyList())
    val recentList: LiveData<List<RecentSearch>>
        get() = _recentList

    private val _showMovieSearchEvent = SingleLiveEvent<String>()
    val showMovieSearchEvent: LiveData<String>
        get() = _showMovieSearchEvent

    private val _showSearchRecentFailedEvent = MutableLiveData<Exception>()
    val showSearchRecentFailedEvent: LiveData<Exception>
        get() = _showSearchRecentFailedEvent

    val onChipClick: (String) -> Unit = {
        _showMovieSearchEvent.value = it
    }

    private suspend fun getRecentKeywords() {
        try {
            val keywords = repository.searchRecent()
            _recentList.value = keywords
        } catch (e: Exception) {
            _showSearchRecentFailedEvent.value = e
        }
    }
}