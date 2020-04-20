package com.example.kyudong3.ui.movie.presenter

import com.example.kyudong3.data.local.MovieDao
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.repository.MovieRepository
import com.example.kyudong3.data.repository.MovieRepositoryImpl
import com.example.kyudong3.mapper.MovieLocalMapper
import com.example.kyudong3.mapper.MovieRemoteMapper

class MoviePresenter(
    private val view: MovieContract.View,
    private val movieDao: MovieDao
) : MovieContract.Presenter {

    private val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(movieDao, MovieRemoteMapper(), MovieLocalMapper())
    }

    override fun searchMovie(query: String) {
        if (query.isEmpty()) {
            view.showInvalidQuerySearch()
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
                    view.showEmptySearchResult()
                } else {
                    view.setMovieData(movieList)
                    saveMovieDataLocal(movieList)
                }
            },
            failure = { error ->
                view.showNetworkError(error)
                //showCachedMovieData(searchQuery)
            })
    }

    private fun saveMovieDataLocal(movieList: List<Movie>) {
        val thread = Thread {
            movieRepository.saveMovieDataLocal(movieList)
        }
        thread.start()
    }

    private fun showCachedMovieData(searchQuery: String) {
        view.showCachedMovieData(movieRepository.getMovieListLocal(searchQuery))
    }
}