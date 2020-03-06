package com.example.archstudy.ui.main

import android.util.Log
import com.example.archstudy.data.repository.NaverQueryRepositoryImpl
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.data.source.local.MovieDataDao
import com.example.archstudy.data.source.local.NaverQueryLocalDataSourceImpl
import com.example.archstudy.data.source.local.SearchWordDao
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl

class MainPresenter(
    private val view: MainContract.View,
    localMovieDao: MovieDataDao,
    searchWordDao: SearchWordDao
) : MainContract.Presenter {

    private val localData = NaverQueryLocalDataSourceImpl(localMovieDao, searchWordDao)
    private val remoteData = NaverQueryRemoteDataSourceImpl()
    private var naverQueryRepositoryImpl = NaverQueryRepositoryImpl(localData, remoteData)

    override fun initData() {
        getQuery {
            getData(it)
        }
    }

    override fun getData(query: String?) {

        Log.d("query", "requestQuery in getData() : $query")
        getLocalData(query, successCallback = {
            Log.d("data", "movie data in getLocalData successCallback : $it")
            view.showDataList(it)
        }, failCallback = {
            Log.d("search", "searchQuery in getData() : $it")
            view.showErrorMessage(Throwable(it))
        })
    }

    override fun getQuery(successCallback: (String) -> Unit) {

        naverQueryRepositoryImpl.apply {
            RequestLocalQueryAsync(object : NaverQueryRepositoryImpl.AsyncTaskQueryListener {
                override fun onResult(result: String) {
                    successCallback(result)
                    Log.d("query", "localQuery Result in getQuery() : $result")
                }
            }).execute()
        }
    }

    override fun getLocalData(
        query: String?,
        successCallback: (MutableList<MovieData>) -> Unit,
        failCallback: (String) -> Unit
    ) {

        // query 가 비어있거나 null 값이 아닐 경우 로컬 DB에 데이터 요청
        if (!(query.isNullOrEmpty())) {
            naverQueryRepositoryImpl.apply {
                RequestLocalDataAsync(
                    query,
                    object : NaverQueryRepositoryImpl.AsyncTaskDataListener {
                        override fun onResult(result: MutableList<MovieData>) {

                            if (result.size != 0) {
                                successCallback(result.asReversed())
                            } else {
                                failCallback(query)
                            }
                        }
                    }).execute()
            }
        }
    }

    override fun insertData(query: String, data: MutableList<MovieData>) {
        Log.d("insert", "query : $query")
        Log.d("insert", "data : $data")
        naverQueryRepositoryImpl.InsertLocalDataAsync(query, data).execute()
    }
}