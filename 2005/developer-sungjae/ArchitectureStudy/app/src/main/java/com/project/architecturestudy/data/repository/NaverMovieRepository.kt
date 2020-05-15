package com.project.architecturestudy.data.repository

import android.content.Context
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

interface NaverMovieRepository {

    var context: Context
    var naverMovieLocalDataSource: NaverMovieLocalDataSource
    var naverMovieRemoteDataSource: NaverMovieRemoteDataSource

    fun setRepository(context: Context, naverMovieLocalDataSource: NaverMovieLocalDataSource, naverMovieRemoteDataSource: NaverMovieRemoteDataSource)

    fun getMovieList(
        keyWord: String,
        SuccessMsg: () -> Unit,
        FailureMsg: () -> Unit
    )

    fun getCashedMovieList()
}