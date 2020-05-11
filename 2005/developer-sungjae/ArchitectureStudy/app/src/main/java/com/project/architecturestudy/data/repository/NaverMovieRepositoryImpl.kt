package com.project.architecturestudy.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

class NaverMovieRepositoryImpl(
    val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) :
    NaverMovieRepository {
    @SuppressLint("CheckResult")
    override fun getMovieList(
        keyWord: String, adapter: SearchAdapter?,
        SuccessMsg: (String) -> Unit,
        FailureMsg: (String) -> Unit
    ) {

        naverMovieRemoteDataSource.getMovieList(keyWord,
            Success = { items ->
                adapter?.resetData(items)
            },
            Failure = {
                Log.d("bsjbsj", "Failure : $it")
            }
        )

    }

}