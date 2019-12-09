package com.example.androidarchitecture.data.datasource.remote

import com.example.androidarchitecture.apis.NetworkUtil
import com.example.androidarchitecture.data.response.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverRemoteDs :
    NaverRemoteDsInterface {
    override fun getBlog(
        query: String,
        start: Int,
        display: Int,
        success: (List<BlogData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NetworkUtil.apiService.getBlogList(query, start, display)
            .enqueue(object : Callback<NaverQueryResponse<BlogData>> {
                override fun onFailure(call: Call<NaverQueryResponse<BlogData>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<NaverQueryResponse<BlogData>>,
                    response: Response<NaverQueryResponse<BlogData>>
                ) {
                    if (response.isSuccessful)
                        response.body()?.items?.let { success(it) }
                }
            })
    }

    override fun getImage(
        query: String,
        start: Int,
        display: Int,
        success: (List<ImageData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NetworkUtil.apiService.getImageList(query, start, display)
            .enqueue(object : Callback<NaverQueryResponse<ImageData>> {
                override fun onFailure(call: Call<NaverQueryResponse<ImageData>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<NaverQueryResponse<ImageData>>,
                    response: Response<NaverQueryResponse<ImageData>>
                ) {
                    if (response.isSuccessful)
                        response.body()?.items?.let {
                            success(it)
                        }
                }
            })
    }

    override fun getKin(
        query: String,
        start: Int,
        display: Int,
        success: (List<KinData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NetworkUtil.apiService.getKinList(query, start, display)
            .enqueue(object : Callback<NaverQueryResponse<KinData>> {
                override fun onFailure(call: Call<NaverQueryResponse<KinData>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<NaverQueryResponse<KinData>>,
                    response: Response<NaverQueryResponse<KinData>>
                ) {
                    if (response.isSuccessful)
                        response.body()?.items?.let {
                            success(it)
                        }
                }
            })
    }

    override fun getMovie(
        query: String,
        start: Int,
        display: Int,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        NetworkUtil.apiService.getMovieList(query, start, display)
            .enqueue(object : Callback<NaverQueryResponse<MovieData>> {
                override fun onFailure(call: Call<NaverQueryResponse<MovieData>>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(
                    call: Call<NaverQueryResponse<MovieData>>,
                    response: Response<NaverQueryResponse<MovieData>>
                ) {

                    response.body()?.items?.let {
                        success(it)
                    }
                }
            })
    }
}