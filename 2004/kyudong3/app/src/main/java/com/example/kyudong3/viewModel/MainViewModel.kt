package com.example.kyudong3.viewModel

import androidx.databinding.ObservableField
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.repository.MovieRepository

class MainViewModel(private val movieRepository: MovieRepository) {

    val movies: ObservableField<List<Movie>> = ObservableField()
    val searchQuery: ObservableField<String> = ObservableField()
    val invalidSearchQuery: ObservableField<Unit> = ObservableField()
    val emptySearchResult: ObservableField<Unit> = ObservableField()
    val showNetworkError: ObservableField<Unit> = ObservableField()

    fun searchMovie() {
        val query = searchQuery.get() as String
        if (query.isEmpty()) {
            invalidSearchQuery.notifyChange()
            return
        } else {
            fetchMovieList(query)
        }
    }

    private fun fetchMovieList(searchQuery: String) {
        fetchMovieFromRemote(searchQuery)
    }

    private fun fetchMovieFromRemote(searchQuery: String) {
        movieRepository.getMovieListRemote(searchQuery,
            success = { movieList: List<Movie> ->
                if (movieList.isEmpty()) {
                    emptySearchResult.notifyChange()
                } else {
                    movies.set(movieList)
                }
            },
            failure = { error ->
                showNetworkError.notifyChange()
            })
    }
}