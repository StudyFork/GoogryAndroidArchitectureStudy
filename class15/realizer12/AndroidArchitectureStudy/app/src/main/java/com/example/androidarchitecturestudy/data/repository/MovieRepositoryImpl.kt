package com.example.androidarchitecturestudy.data.repository

import android.content.Context
import com.example.androidarchitecturestudy.data.GetMovieInfo
import com.example.androidarchitecturestudy.data.datasource.MovieLocalDataSourceImpl
import com.example.androidarchitecturestudy.data.datasource.MovieRemoteDataSourceImpl
import com.example.androidarchitecturestudy.room.SearchedDataBase

class MovieRepositoryImpl() :MovieRepository{

    private val remoteMovieDataSource = MovieRemoteDataSourceImpl()
    private val localMovieDataSource = MovieLocalDataSourceImpl()

    override fun saveRecentSearch(searchQuery: String,dataBase: SearchedDataBase) {
      localMovieDataSource.saveRecentSearch(searchQuery,dataBase)
    }


    override fun getRecentSearch(dataBase: SearchedDataBase): List<String> = localMovieDataSource.getRecentSearchedMovieList(dataBase)


    override fun getMovieSearchResult(
        movieName: String,
        onSuccess: (GetMovieInfo.MovieList) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {

        remoteMovieDataSource.getMovieSearchResult(
            movieName, onSuccess, onFailure
        )
    }

}