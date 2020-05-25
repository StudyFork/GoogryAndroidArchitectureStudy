package com.example.kyudong3.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kyudong3.R
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.repository.MovieRepository
import com.example.kyudong3.provider.ResourceProvider

class MainViewModel(
    private val movieRepository: MovieRepository,
    private val resourceProvider: ResourceProvider
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    private val _invalidSearchQuery = MutableLiveData<String>()
    val invalidSearchQuery: LiveData<String>
        get() = _invalidSearchQuery

    private val _emptySearchResult = MutableLiveData<String>()
    val emptySearchResult: LiveData<String>
        get() = _emptySearchResult

    private val _showNetworkError = MutableLiveData<String>()
    val showNetworkError: LiveData<String>
        get() = _showNetworkError

    val searchQuery = MutableLiveData<String>()

    fun searchMovie() {
        val query = searchQuery.value
        if (query.isNullOrBlank()) {
            _invalidSearchQuery.value =
                resourceProvider.getString(R.string.toast_invalid_search_query)
        } else {
            fetchMovieList(query)
        }
    }

    private fun fetchMovieList(searchQuery: String) {
        movieRepository.getMovieListRemote(searchQuery,
            success = { movieList: List<Movie> ->
                if (movieList.isEmpty()) {
                    _emptySearchResult.value =
                        resourceProvider.getString(R.string.toast_empty_search_result)
                } else {
                    _movies.value = movieList
                }
            },
            failure = {
                _showNetworkError.value =
                    resourceProvider.getString(R.string.toast_show_network_error)
            })
    }

    override fun onCleared() {
        super.onCleared()
    }
}