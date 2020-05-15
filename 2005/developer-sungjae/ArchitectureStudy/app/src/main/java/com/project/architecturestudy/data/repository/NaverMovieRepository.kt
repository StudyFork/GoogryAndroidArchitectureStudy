package com.project.architecturestudy.data.repository

import android.content.Context
import com.project.architecturestudy.data.model.Movie
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.local.room.MovieLocal
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

interface NaverMovieRepository {

    var context: Context
    var naverMovieLocalDataSource: NaverMovieLocalDataSource
    var naverMovieRemoteDataSource: NaverMovieRemoteDataSource

    fun setRepository(context: Context, naverMovieLocalDataSource: NaverMovieLocalDataSource, naverMovieRemoteDataSource: NaverMovieRemoteDataSource)
    fun getMovieList(keyWord: String, Success: (ArrayList<Movie.Items>) -> Unit, Failure: (Throwable) -> Unit)
    fun getCashedMovieList(Success: (ArrayList<MovieLocal>) -> Unit, Failure: (Throwable) -> Unit)
}