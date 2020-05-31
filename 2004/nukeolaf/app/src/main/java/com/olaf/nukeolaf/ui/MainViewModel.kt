package com.olaf.nukeolaf.ui

import android.app.Application
import android.os.Build
import android.text.Html
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.olaf.nukeolaf.data.local.MovieLocalDataSourceImpl
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.data.remote.MovieRemoteDataSourceImpl
import com.olaf.nukeolaf.data.repository.MovieRepository
import com.olaf.nukeolaf.data.repository.MovieRepositoryImpl

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val movieRepository: MovieRepository = MovieRepositoryImpl(
        MovieLocalDataSourceImpl(application.applicationContext),
        MovieRemoteDataSourceImpl()
    )

    val movies = MutableLiveData<List<MovieItem>>()
    val errorType = MutableLiveData<Int>().apply { value = NO_ERROR }

    init {
        loadMovies()
    }

    private fun loadMovies() {
        val movieList = movieRepository.getMovies()
        movies.value = movieList?.items?.processMovieItemString() ?: listOf()
    }

    fun searchMovie(query: String?) {
        if (query.isNullOrEmpty()) {
            errorType.value = EMPTY_SEARCH_WORD
            return
        }
        movieRepository.searchMovies(
            query,
            object : MovieRepository.LoadMoviesCallback {
                override fun onMoviesLoaded(movieResponse: MovieResponse) {
                    if (movieResponse.items.isNotEmpty()) {
                        movies.value = movieResponse.items.processMovieItemString()
                        errorType.value = NO_ERROR
                    } else {
                        errorType.value = NO_QUERY_RESULT
                    }
                }

                override fun onResponseError(message: String) {
                    errorType.value = SERVER_ERROR
                }

                override fun onFailure(t: Throwable) {
                    errorType.value = NETWORK_ERROR
                }
            })
    }

    private fun List<MovieItem>.processMovieItemString(): List<MovieItem> {
        return this.map {
            it.copy(
                title = it.title.htmlToString(),
                subtitle = it.subtitle.htmlToString(),
                director = it.director.htmlToString().addCommas(),
                actor = it.actor.htmlToString().addCommas(),
                userRating = it.userRating / 2
            )
        }
    }

    private fun String.htmlToString(): String {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            Html.fromHtml(this).toString()
        }
    }

    private fun String.addCommas(): String {
        return if (this.isNotBlank()) {
            this.substring(0, this.length - 1)
                .split("|")
                .joinToString()
        } else {
            this
        }
    }

}