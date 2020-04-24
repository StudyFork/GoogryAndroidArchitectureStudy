package com.example.kyudong3.data.repository

import com.example.kyudong3.data.local.MovieDao
import com.example.kyudong3.data.local.MovieLocalDataSource
import com.example.kyudong3.data.local.MovieLocalDataSourceImpl
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.remote.MovieRemoteDataSource
import com.example.kyudong3.data.remote.MovieRemoteDataSourceImpl
import com.example.kyudong3.mapper.MovieLocalMapper
import com.example.kyudong3.mapper.MovieRemoteMapper

class MovieRepositoryImpl(
    private val movieDao: MovieDao,
    private val movieRemoteMapper: MovieRemoteMapper,
    private val movieLocalMapper: MovieLocalMapper
) : MovieRepository {

    private val movieRemoteDataSourceImpl: MovieRemoteDataSource by lazy {
        MovieRemoteDataSourceImpl(movieRemoteMapper)
    }

    private val movieLocalDataSourceImpl: MovieLocalDataSource by lazy {
        MovieLocalDataSourceImpl(movieDao, movieLocalMapper)
    }

    override fun getMovieListRemote(
        searchQuery: String,
        success: (List<Movie>) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieRemoteDataSourceImpl.getMovieList(
            query = searchQuery,
            success = { movieList: List<Movie> ->
                Thread {
                    saveMovieDataLocal(movieList)
                }.start()
                success(movieList)
            },
            failure = failure
        )
    }

    override fun getMovieListLocal(searchQuery: String): List<Movie> {
        return movieLocalDataSourceImpl.getMovieList(query = searchQuery)
    }

    override fun saveMovieDataLocal(movieList: List<Movie>) {
        movieLocalDataSourceImpl.saveMovieList(movieList)
    }
}