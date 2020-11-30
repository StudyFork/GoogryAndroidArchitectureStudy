package com.example.googryandroidarchitecturestudy.ui.viewmodel

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.googryandroidarchitecturestudy.database.MovieDatabase

abstract class MovieViewModel(
    private val context: Context
) : BaseViewModel() {
    protected val repository: MovieRepository by lazy {
        val movieRemoteDataSource = MovieRemoteDataSourceImpl()
        val movieLocalDataSource =
            MovieLocalDataSourceImpl(MovieDatabase.getInstance(context))
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }
}