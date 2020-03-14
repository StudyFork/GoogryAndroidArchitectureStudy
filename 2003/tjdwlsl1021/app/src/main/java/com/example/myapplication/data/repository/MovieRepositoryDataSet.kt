package com.example.myapplication.data.repository

import android.app.Application
import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.local.source.MovieLocalDataSourceImpl
import com.example.myapplication.data.remote.MovieRemoteDataSource
import com.example.myapplication.data.remote.MovieRemoteDataSourceImpl

class MovieRepositoryDataSet: Application() {
    lateinit var MovieRepository: MovieRepository
    private lateinit var MovieRemoteDataSource: MovieRemoteDataSource
    private lateinit var MovieLocalDataSource: MovieLocalDataSource
    private lateinit var MovieDao: MovieDao


    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        MovieDao = MovieDatabase.getDatabase(this).movieDao()
        MovieRemoteDataSource = MovieRemoteDataSourceImpl()
        MovieLocalDataSource = MovieLocalDataSourceImpl(MovieDao)
        MovieRepository = MovieRepositoryImpl(MovieRemoteDataSource, MovieLocalDataSource)
    }
}