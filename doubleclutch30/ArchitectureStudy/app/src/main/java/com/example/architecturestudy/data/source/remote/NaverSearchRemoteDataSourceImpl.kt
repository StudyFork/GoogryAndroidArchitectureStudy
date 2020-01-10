package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.network.Api
import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl(private val restClient: Api) : NaverSearchRemoteDataSource {

    override fun getMovie(keyword: String, success: (List<MovieItem>) -> Unit, fail: (Throwable) -> Unit) {
        restClient.requestMovies(keyword).enqueue(object : Callback<MovieData> {
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if(response.isSuccessful) {
                    success(response.body()?.items ?: emptyList())
                }
            }
        })
    }

    override fun getBlog(keyword: String, success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit) {
        restClient.requestBlog(keyword).enqueue(object : Callback<BlogData> {
            override fun onFailure(call: Call<BlogData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<BlogData>, response: Response<BlogData>) {
                if(response.isSuccessful) {
                    success(response.body()?.items ?: emptyList())
                }
            }
        })
    }

    override fun getKin(keyword: String, success: (List<KinItem>) -> Unit, fail: (Throwable) -> Unit) {
        restClient.requestKin(keyword).enqueue(object : Callback<KinData> {
            override fun onFailure(call: Call<KinData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<KinData>, response: Response<KinData>) {
                if(response.isSuccessful) {
                    success(response.body()?.items ?: emptyList())
                }
            }
        })
    }

    override fun getImage(keyword: String, success: (List<ImageItem>) -> Unit, fail: (Throwable) -> Unit)
    {
        restClient.requestImage(keyword).enqueue(object : Callback<ImageData> {
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if(response.isSuccessful) {
                    success(response.body()?.items ?: emptyList())
                }
            }
        })
    }
}