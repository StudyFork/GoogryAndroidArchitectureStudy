package com.project.architecturestudy.data.repository

import android.content.Context
import android.util.Log
import com.project.architecturestudy.adapters.SearchAdapter
import com.project.architecturestudy.data.source.local.NaverMovieLocalDataSource
import com.project.architecturestudy.data.source.remote.NaverMovieRemoteDataSource

object NaverMovieRepositoryImpl : NaverMovieRepository {

    override lateinit var context: Context
    override lateinit var naverMovieLocalDataSource: NaverMovieLocalDataSource
    override lateinit var naverMovieRemoteDataSource: NaverMovieRemoteDataSource
    private lateinit var adapter: SearchAdapter


    override fun setRepository(
        context: Context,
        naverMovieLocalDataSource: NaverMovieLocalDataSource,
        naverMovieRemoteDataSource: NaverMovieRemoteDataSource
    ) {
        this.context = context
        this.naverMovieLocalDataSource = naverMovieLocalDataSource
        this.naverMovieRemoteDataSource = naverMovieRemoteDataSource
    }

    override fun getCashedMovieList() {
        naverMovieLocalDataSource.getMovieList(context,
            Success = { items ->
                adapter.setLocalMovieData(items)
                Log.d("bsjbsj", "RoomDataBase LoadData Success")
            },
            Failure = {
                Log.d("bsjbsj", "RoomDataBase LoadData Failure : $it")
            })
    }

    override fun getMovieList(
        keyWord: String,
        SuccessMsg: () -> Unit,
        FailureMsg: () -> Unit
    ) {
        naverMovieRemoteDataSource.getMovieList(keyWord,
            Success = { items ->
                adapter.setRemoteMovieData(items)
                naverMovieLocalDataSource.saveMovieList(items, context)
                SuccessMsg.invoke()
            },
            Failure = {
                Log.d("bsjbsj", "Failure : $it")
                FailureMsg.invoke()
            }
        )
    }

}