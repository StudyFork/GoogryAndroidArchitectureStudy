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
        Api.getMovies(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Movie>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Movie>>, t: Throwable) {
                    Log.e("Movie", "error=${t.message}")
                    fail(t)
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Movie>>,
                    response: Response<ResponseNaverQuery<Movie>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseMovie(it.items)) }
                    }
                }

            })
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