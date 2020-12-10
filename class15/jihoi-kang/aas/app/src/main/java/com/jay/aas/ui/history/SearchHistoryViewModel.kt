package com.jay.aas.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jay.aas.base.BaseViewModel
import com.jay.aas.data.MovieRepository
import com.jay.aas.model.SearchHistory
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    private val _searchHistoryItems = MutableLiveData<List<SearchHistory>>()
    val searchHistoryItems: LiveData<List<SearchHistory>> get() = _searchHistoryItems
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _searchQuery
    private val _finishEvent = MutableLiveData<Unit>()
    val finishEvent: LiveData<Unit> get() = _finishEvent

    fun getSearchHistories() {
        viewModelScope.launch {
            val searchHistories = movieRepository.getSearchHistories()
            _searchHistoryItems.value = searchHistories
        }
    }

    fun searchMovies(query: String) {
        _searchQuery.value = query
    }

    fun finish() {
        _finishEvent.value = Unit
    }

}