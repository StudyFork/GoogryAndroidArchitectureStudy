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

    private lateinit var context: Context
    private var adapter: SearchAdapter? = null


    override fun getCashedMovieList(context: Context, adapter: SearchAdapter?) {
        this.context = context
        this.adapter = adapter

        naverMovieLocalDataSource.getMovieList(context,
            Success = { items ->
                adapter?.setLocalMovieData(items)
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
                adapter?.setRemoteMovieData(items)
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