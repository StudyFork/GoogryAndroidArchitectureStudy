package com.jay.architecturestudy.data.source.remote

import android.util.Log
import com.jay.architecturestudy.data.model.*
import com.jay.architecturestudy.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {
    override fun getMovie(
        keyword: String,
        success: (ResponseMovie) -> Unit,
        fail: (Throwable) -> Unit
    ) {
    }

    override fun getImage(
        keyword: String,
        success: (ResponseImage) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBlog(
        keyword: String,
        success: (ResponseBlog) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKin(
        keyword: String,
        success: (ResponseKin) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}