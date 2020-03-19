package com.example.myapplication.data.repository

import android.app.Application
import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.local.source.MovieLocalDataSourceImpl
import com.example.myapplication.data.remote.source.MovieRemoteDataSource
import com.example.myapplication.data.remote.source.MovieRemoteDataSourceImpl

class MovieRepositoryDataSet : Application() {
    lateinit var movieRepository: MovieRepository
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource
    private lateinit var movieLocalDataSource: MovieLocalDataSource
    private lateinit var movieDao: MovieDao


    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        movieDao = MovieDatabase.getDatabase(this).movieDao()
        movieRemoteDataSource =
            MovieRemoteDataSourceImpl()
        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }
}