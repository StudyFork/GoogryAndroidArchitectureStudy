package com.mtjin.androidarchitecturestudy.utils

import android.app.Application
import com.mtjin.androidarchitecturestudy.data.source.MovieRepository
import com.mtjin.androidarchitecturestudy.data.source.MovieRepositoryImpl
import com.mtjin.androidarchitecturestudy.data.source.local.MovieDao
import com.mtjin.androidarchitecturestudy.data.source.local.MovieDatabase
import com.mtjin.androidarchitecturestudy.data.source.local.MovieLocalDataSource
import com.mtjin.androidarchitecturestudy.data.source.local.MovieLocalDataSourceImpl
import com.mtjin.androidarchitecturestudy.data.source.remote.MovieRemoteDataSource
import com.mtjin.androidarchitecturestudy.data.source.remote.MovieRemoteDataSourceImpl

class MyApplication : Application() {
    lateinit var movieRepository: MovieRepository
    lateinit var movieRemoteDataSource: MovieRemoteDataSource
    lateinit var movieLocalDataSource: MovieLocalDataSource
    lateinit var movieDao: MovieDao

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        movieDao = MovieDatabase.getInstance(this).movieDao()
        movieRemoteDataSource = MovieRemoteDataSourceImpl()
        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }
}