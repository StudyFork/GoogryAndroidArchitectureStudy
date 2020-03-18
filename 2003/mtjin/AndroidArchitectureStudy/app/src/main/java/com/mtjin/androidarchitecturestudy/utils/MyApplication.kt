package com.mtjin.androidarchitecturestudy.utils

import android.app.Application
import com.mtjin.androidarchitecturestudy.api.ApiClient
import com.mtjin.androidarchitecturestudy.api.ApiInterface
import com.mtjin.androidarchitecturestudy.data.source.MovieRepository
import com.mtjin.androidarchitecturestudy.data.source.MovieRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.source.local.movie_search.MovieDao
import com.mtjin.androidarchitecturestudy.data.source.local.movie_search.MovieDatabase
import com.mtjin.androidarchitecturestudy.data.source.local.movie_search.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.source.local.movie_search.MovieLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.data.source.remote.movie_search.MovieRemoteDataSource
import com.mtjin.androidarchitecturestudy.data.source.remote.movie_search.MovieRemoteDataSourceImpl

class MyApplication : Application() {
    private lateinit var networkManager: NetworkManager
    private lateinit var apiInterface: ApiInterface
    lateinit var movieRepository: MovieRepository
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private lateinit var movieLocalDataSource: MovieLocalDataSource
    private lateinit var movieDao: MovieDao

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        networkManager = NetworkManager(applicationContext)
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
        movieDao = MovieDatabase.getInstance(this).movieDao()
        movieRemoteDataSource =
            MovieRemoteDataSourceImpl(
                apiInterface
            )
        movieLocalDataSource =
            MovieLocalDataSourceImpl(
                movieDao
            )
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, networkManager)
    }
}