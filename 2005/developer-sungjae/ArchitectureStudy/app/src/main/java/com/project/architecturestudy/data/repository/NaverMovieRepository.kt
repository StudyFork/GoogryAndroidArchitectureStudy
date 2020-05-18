package com.project.architecturestudy.data.repository

import android.content.Context
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSourceImpl
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSourceImpl

interface NaverMovieRepository {

    var context: Context
    var naverMovieLocalDataSource: NaverMovieLocalDataSourceImpl
    var naverMovieRemoteDataSource: NaverMovieRemoteDataSourceImpl

    fun setRepository(context: Context, naverMovieLocalDataSource: NaverMovieLocalDataSourceImpl, naverMovieRemoteDataSource: NaverMovieRemoteDataSourceImpl)
    fun getMovieList(keyWord: String, Success: (ArrayList<Movie.Items>) -> Unit, Failure: (Throwable) -> Unit)
    fun getCashedMovieList(Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit)
}