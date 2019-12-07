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
        Api.getImages(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Image>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Image>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Image>>,
                    response: Response<ResponseNaverQuery<Image>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseImage(it.items)) }
                    }
                }
            })
    }

    override fun getBlog(
        keyword: String,
        success: (ResponseBlog) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        Api.getBlog(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Blog>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Blog>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Blog>>,
                    response: Response<ResponseNaverQuery<Blog>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseBlog(it.items)) }
                    }
                }

            })
    }

    override fun getKin(
        keyword: String,
        success: (ResponseKin) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        Api.getKin(keyword)
            .enqueue(object : Callback<ResponseNaverQuery<Kin>> {
                override fun onFailure(call: Call<ResponseNaverQuery<Kin>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<ResponseNaverQuery<Kin>>,
                    response: Response<ResponseNaverQuery<Kin>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { success(ResponseKin(it.items)) }
                    }
                }

            })
    }
}