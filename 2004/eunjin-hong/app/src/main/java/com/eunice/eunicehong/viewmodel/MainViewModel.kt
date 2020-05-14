package com.eunice.eunicehong.viewmodel

import android.util.Log
import androidx.databinding.ObservableField
import com.eunice.eunicehong.data.model.MovieList
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.ui.MovieAdapter
import com.eunice.eunicehong.ui.MovieCache

class MainViewModel(private val cache: MovieCache) {

    val movieListAdapter = MovieAdapter()

    val movieListState: ObservableField<MovieListState> =
        ObservableField(MovieListState.EMPTY_QUERY)

    fun search(query: String?) {
        if (query.isNullOrBlank()) return

        cache.saveSearchRecentSuggestions(query)

        val callback = object : MovieDataSource.LoadMoviesCallback {
            override fun onSuccess(movieList: MovieList) {
                showSearchResult(query, movieList)
            }

            override fun onFailure(e: Throwable) {
                Log.d(this.toString(), e.toString())
            }
        }

        movieListState.set(MovieListState.LOADING)

        try {
            val list = cache.getMovieList(query)
            if (list.items.isEmpty()) {
                MovieRepository.getMovieList(query, callback)
            } else {
                callback.onSuccess(list)
            }
        } catch (e: Throwable) {
            MovieRepository.getMovieList(query, callback)
        }
    }
    fun removeHistory() {
        cache.removeMovieHistory()
    }


    fun showSearchResult(query: String, movies: MovieList) {
        if (movies.items.isEmpty()) {
            movieListState.set(MovieListState.NO_MATCHING_RESULT)
        } else {
            movieListAdapter.setMovieList(movies.items)
            movieListState.set(MovieListState.SHOW_RESULT)
        }
        cache.saveMovieList(query, movies)
    }

    fun setListStateEmptyQuery() {
        movieListState.set(MovieListState.EMPTY_QUERY)
    }

    enum class MovieListState {
        NETWORK_ERROR,
        NO_MATCHING_RESULT,
        SHOW_RESULT,
        LOADING,
        EMPTY_QUERY
    }
}