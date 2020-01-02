package com.example.studyapplication.data.datasource.remote

import android.util.Log
import com.example.studyapplication.network.ApiClient
import com.example.studyapplication.network.Conn
import com.example.studyapplication.network.Remote

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override fun getMovieList(title : String, conn : Conn) {
//        Remote.get(ApiClient.getService().getMovieList(title), object : Conn {
//            override fun <T> success(result: T) {
//                conn.success(result)
//            }
//
//            override fun failed(errorMessage: String) {
//                conn.failed(errorMessage)
//            }
//
//        })
    }

    override fun getBlogList(title: String, conn: Conn) {
        Remote.get(ApiClient.getService().getBlogList(title), object : Conn {
            override fun <T> success(result: T) {
//                conn.success(result)
                Log.e("", ">>> getBlogList()")
                conn.failed(Throwable("Test"))
            }

            override fun failed(e: Throwable) {
                conn.failed(e)
            }
        })
    }

    override fun getImageList(title: String, conn: Conn) {
//        Remote.get(ApiClient.getService().getImageList(title), object : Conn {
//            override fun <T> success(result: T) {
//                conn.success(result)
//            }
//
//            override fun failed(errorMessage: String) {
//                conn.failed(errorMessage)
//            }
//        })
    }

    override fun getKinList(title: String, conn: Conn) {
//        Remote.get(ApiClient.getService().getKinList(title), object : Conn {
//            override fun <T> success(result: T) {
//                conn.success(result)
//            }
//
//            override fun failed(errorMessage: String) {
//                conn.failed(errorMessage)
//            }
//        })
    }

}