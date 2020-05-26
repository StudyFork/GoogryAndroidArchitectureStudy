package com.olaf.nukeolaf.ui

import android.os.Build
import android.text.Html
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.olaf.nukeolaf.data.model.MovieItem
import com.olaf.nukeolaf.data.model.MovieResponse
import com.olaf.nukeolaf.data.repository.MovieRepository

class MainViewModel(
    private val movieRepository: MovieRepository
) : ViewModel() {

    val movies = MutableLiveData<List<MovieItem>>()
    val errorType = MutableLiveData<Int>().apply { value = 0 }

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
                director = it.director.addCommas("감독 : "),
                actor = it.actor.addCommas("출연진 : "),
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

    private fun String.addCommas(prefix: String): String {
        return if (this.isNotBlank()) {
            this.substring(0, this.length - 1)
                .split("|")
                .joinToString(
                    prefix = prefix,
                    separator = ", "
                )
        } else {
            this
        }
    }

}