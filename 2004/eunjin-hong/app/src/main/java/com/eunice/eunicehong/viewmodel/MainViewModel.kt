package com.eunice.eunicehong.viewmodel

import android.app.Application
import android.provider.SearchRecentSuggestions
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.eunice.eunicehong.data.model.Movie
import com.eunice.eunicehong.data.model.MovieContents
import com.eunice.eunicehong.data.source.MovieDataSource
import com.eunice.eunicehong.data.source.MovieRepository
import com.eunice.eunicehong.data.source.local.MovieLocalDataSource
import com.eunice.eunicehong.data.source.remote.MovieRemoteDataSource
import com.eunice.eunicehong.provider.SuggestionProvider
import com.eunice.eunicehong.ui.MoviePreferences

class MainViewModel(application: Application) :
    AndroidViewModel(application) {

    private val sharedPreferences = MoviePreferences(application.applicationContext)
    private val searchRecentSuggestions = SearchRecentSuggestions(
        application.applicationContext,
        SuggestionProvider.AUTHORITY,
        SuggestionProvider.MODE
    )

    private val localDataSource = MovieLocalDataSource(sharedPreferences, searchRecentSuggestions)
    private val remoteDataSource = MovieRemoteDataSource()
    val movieRepository = MovieRepository(remoteDataSource, localDataSource)

    val movieListState: MutableLiveData<MovieListState> =
        MutableLiveData(MovieListState.EMPTY_QUERY)

    private val loadMovieListCallback = object : MovieDataSource.LoadMoviesCallback {
        override fun onSuccess(query: String?, movieContents: MovieContents) {
            if (query.isNullOrBlank()) {
                setListStateEmptyQuery()
            } else {
                resultMovieList.value = movieContents.items
                showSearchResult(movieContents)
                movieRepository.saveMovieList(query, movieContents)
            }
        }

        override fun onFailure(e: Throwable) {
            Log.d(this.toString(), e.toString())
        }
    }

    val resultMovieList = MutableLiveData<Collection<Movie>>()

    fun search(query: String?) {
        if (query.isNullOrBlank()) return
        movieListState.value = MovieListState.LOADING
        movieRepository.getMovieList(query, loadMovieListCallback)
    }

    fun deleteAllSearchRecentSuggestions() {
        movieRepository.deleteAllSearchRecentSuggestions()
    }

    fun removeHistory() {
        movieRepository.removeMovieHistory()
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