package com.project.architecturestudy.data.repository

import android.util.Log
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) :
    NaverMovieRepository {

    override fun getMovieList(keyWord: String, adapter: SearchAdapter?, SuccessMsg: () -> Unit, FailureMsg: () -> Unit) {
        naverMovieRemoteDataSource.getMovieList(keyWord,
            Success = { items ->
                adapter?.resetData(items)
                SuccessMsg.invoke()
            },
            Failure = {
                Log.d("bsjbsj", "Failure : $it")
                FailureMsg.invoke()
            }
        )
    }

}