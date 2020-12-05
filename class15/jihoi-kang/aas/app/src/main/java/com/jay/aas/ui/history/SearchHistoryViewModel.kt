package com.jay.aas.ui.history

import androidx.databinding.ObservableField
import androidx.lifecycle.viewModelScope
import com.jay.aas.base.BaseViewModel
import com.jay.aas.data.MovieRepository
import com.jay.aas.model.SearchHistory
import kotlinx.coroutines.launch

class SearchHistoryViewModel(
    private val movieRepository: MovieRepository
) : BaseViewModel() {

    val searchHistoryItems = ObservableField<List<SearchHistory>>(emptyList())
    val searchQuery = ObservableField<String>()
    val finishEvent = ObservableField<Unit>()

    fun getSearchHistories() {
        viewModelScope.launch {
            val searchHistories = movieRepository.getSearchHistories()
            searchHistoryItems.set(searchHistories)
        }
    }

    fun searchMovies(query: String) {
        searchQuery.set(query)
    }

    fun finish() {
        finishEvent.notifyChange()
    }

}