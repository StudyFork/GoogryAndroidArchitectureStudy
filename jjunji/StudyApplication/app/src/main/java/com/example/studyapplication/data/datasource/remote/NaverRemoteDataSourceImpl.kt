package com.example.studyapplication.data.datasource.remote

import android.util.Log
import com.example.studyapplication.data.model.SearchData
import com.example.studyapplication.data.model.SearchMovieResult
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.Conn
import com.example.studyapplication.network.Remote
import retrofit2.Call

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {
    override fun getMovieList(title : String, conn : Conn) {
        Remote.get(ApiClient.getService().getMovieList(title), object : Conn {
            override fun <T> success(result: T) {
                conn.success(result)
            }

            override fun failed() {
                conn.failed()
            }
        })
    }

    override fun getBlogList(title: String, conn: Conn) {
        Remote.get(ApiClient.getService().getBlogList(title), object : Conn {
            override fun <T> success(result: T) {
                conn.success(result)
            }

            override fun failed() {
                conn.failed()
            }
        })
    }

}