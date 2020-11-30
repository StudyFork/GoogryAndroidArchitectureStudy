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

    fun getSearchHistories() {
        viewModelScope.launch {
            val searchHistories = movieRepository.getSearchHistories()
            searchHistoryItems.set(searchHistories)
        }
    }

    val finishEvent = ObservableField<String?>(null)

    fun searchMovies(query: String) {
        finishEvent.set(query)
    }

    fun finish() {
        finishEvent.notifyChange()
    }

}