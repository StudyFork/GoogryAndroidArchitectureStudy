package com.eunice.eunicehong.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.data.model.MovieContents
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.ui.MovieCache

class MainViewModel(private val cache: MovieCache) {

    val movieListState: MutableLiveData<MovieListState> =
        MutableLiveData(MovieListState.EMPTY_QUERY)

    private val loadMovieListCallback = object : MovieDataSource.LoadMoviesCallback {
        override fun onSuccess(query: String?, movieContents: MovieContents) {
            if (query.isNullOrBlank()) {
                setListStateEmptyQuery()
            } else {
                resultMovieList.value = movieContents.items
                showSearchResult(movieContents)
                cache.saveMovieList(query, movieContents)
            }
        }

        override fun onFailure(e: Throwable) {
            Log.d(this.toString(), e.toString())
        }
    }

    val resultMovieList = MutableLiveData<Collection<Movie>>()

    fun search(query: String?) {
        if (query.isNullOrBlank()) return

        cache.saveSearchRecentSuggestions(query)

        movieListState.value = MovieListState.LOADING

        try {
            val list = cache.getMovieList(query)
            if (list.items.isEmpty()) {
                MovieRepository.getMovieList(query, loadMovieListCallback)
            } else {
                loadMovieListCallback.onSuccess(query, list)
            }
        } catch (e: Throwable) {
            MovieRepository.getMovieList(query, loadMovieListCallback)
        }
    }

    fun removeHistory() {
        cache.removeMovieHistory()
    }

    fun showSearchResult(movies: MovieContents) {
        if (movies.items.isEmpty()) {
            movieListState.value = MovieListState.NO_MATCHING_RESULT
        } else {
            movieListState.value = MovieListState.SHOW_RESULT
        }
    }

    fun setListStateEmptyQuery() {
        movieListState.value = MovieListState.EMPTY_QUERY
    }

    enum class MovieListState {
        NETWORK_ERROR,
        NO_MATCHING_RESULT,
        SHOW_RESULT,
        LOADING,
        EMPTY_QUERY
    }
}