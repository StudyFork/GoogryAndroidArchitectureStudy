package com.example.myapplication.data.repository

import android.app.Application
import com.example.myapplication.data.local.MovieDao
import com.example.myapplication.data.local.MovieDatabase
import com.example.myapplication.data.local.source.MovieLocalDataSource
import com.example.myapplication.data.local.source.MovieLocalDataSourceImpl
import com.example.myapplication.data.remote.MovieRemoteDataSource
import com.example.myapplication.data.remote.MovieRemoteDataSourceImpl

class MovieRepositoryDataSet: Application() {
    lateinit var mMovieRepository: MovieRepository
    private lateinit var MMovieRemoteDataSource: MovieRemoteDataSource
    private lateinit var mMovieLocalDataSource: MovieLocalDataSource
    private lateinit var MMovieDao: MovieDao


    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        MMovieDao = MovieDatabase.getDatabase(this).movieDao()
        MMovieRemoteDataSource = MovieRemoteDataSourceImpl()
        mMovieLocalDataSource = MovieLocalDataSourceImpl(MMovieDao)
        mMovieRepository = MovieRepositoryImpl(MMovieRemoteDataSource, mMovieLocalDataSource)
    }
}