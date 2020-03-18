package com.mtjin.androidarchitecturestudy.utils

import android.app.Application
import com.mtjin.androidarchitecturestudy.api.ApiClient
import com.mtjin.androidarchitecturestudy.api.ApiInterface
import com.mtjin.androidarchitecturestudy.data.movie_search.source.MovieRepository
import com.mtjin.androidarchitecturestudy.data.movie_search.source.MovieRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.movie_search.source.local.MovieDao
import com.mtjin.androidarchitecturestudy.data.movie_search.source.local.MovieDatabase
import com.mtjin.androidarchitecturestudy.data.movie_search.source.local.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.movie_search.source.local.MovieLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.data.movie_search.source.remote.MovieRemoteDataSource
import com.mtjin.androidarchitecturestudy.data.movie_search.source.remote.MovieRemoteDataSourceImpl

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
        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource, networkManager)
    }
}