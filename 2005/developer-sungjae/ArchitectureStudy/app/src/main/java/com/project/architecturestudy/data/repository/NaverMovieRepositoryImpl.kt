package com.project.architecturestudy.data.repository

import android.content.Context
import android.util.Log
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

class NaverMovieRepositoryImpl(
    private val naverMovieLocalDataSource: NaverMovieLocalDataSource,
    private val naverMovieRemoteDataSource: NaverMovieRemoteDataSource
) :
    NaverMovieRepository {

    override fun getMovieList(
        context: Context,
        keyWord: String, adapter: SearchAdapter?,
        SuccessMsg: () -> Unit,
        FailureMsg: () -> Unit
    ) {

        naverMovieLocalDataSource.getMovieList()

        naverMovieRemoteDataSource.getMovieList(keyWord,
            Success = { items ->
                naverMovieLocalDataSource.saveMovieList(items, context)
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