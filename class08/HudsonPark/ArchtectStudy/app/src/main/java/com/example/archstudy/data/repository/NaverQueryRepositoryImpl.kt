package com.example.archstudy.data.repository

import android.os.AsyncTask
import android.util.Log
import com.example.archstudy.MovieDataResponse
import com.example.archstudy.data.source.local.MovieData
import com.example.archstudy.data.source.local.NaverQueryLocalDataSourceImpl
import com.example.archstudy.data.source.remote.NaverQueryRemoteDataSourceImpl
import retrofit2.Call

class NaverQueryRepositoryImpl(
    private val naverQueryLocalDataSource: NaverQueryLocalDataSourceImpl,
    private val naverQueryRemoteDataSource: NaverQueryRemoteDataSourceImpl
) : NaverQueryRepository {


    override fun requestRemoteData(query: String): Call<MovieDataResponse> {
        return naverQueryRemoteDataSource.getMovie(query)
    }

    override fun requestLocalData(
        query: String
    ): MutableList<MovieData>? {
        return naverQueryLocalDataSource.requestLocalData(query)
    }

    override fun insertLocalData(query: String, data: List<MovieData>) {
        naverQueryLocalDataSource.insertLocalData(query, data.toMutableList())
    }

    inner class RequestLocalDataAsync(private var query: String) :
        AsyncTask<Unit, Unit, MutableList<MovieData>>() {

        override fun doInBackground(vararg param: Unit?): MutableList<MovieData>? {
            Log.d("Async", "RequestLocalDataAsync.doInBackground()")
            val result = requestLocalData(query)
            Log.d("Async", "Request Result : $result")
            return result
        }
    }

    inner class InsertLocalDataAsync(
        private var query: String,
        private var data: List<MovieData>
    ) : AsyncTask<MutableList<MovieData>, Unit, Unit>() {

        override fun doInBackground(vararg p0: MutableList<MovieData>?) {
            insertLocalData(query, data)
            Log.d("Async", "InsertLocalDataAsync.doInBackground()")
        }
    }
}