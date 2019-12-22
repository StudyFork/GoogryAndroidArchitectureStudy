package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.BlogData
import com.example.architecturestudy.data.model.ImageData
import com.example.architecturestudy.data.model.KinData
import com.example.architecturestudy.data.model.MovieData
import com.example.architecturestudy.network.Api
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NaverSearchRemoteDataSourceImpl(private val restClient: Api) : NaverSearchRemoteDataSource {

    override fun getMovie(keyword: String, success: (MovieData) -> Unit, fail: (Throwable) -> Unit) {
        restClient.requestMovies(keyword).enqueue(object : Callback<MovieData> {
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let {
                        success(MovieData(it))
                    }
                }
            }
        })
    }

    override fun getBlog(keyword: String, success: (BlogData) -> Unit, fail: (Throwable) -> Unit) {
        restClient.requestBlog(keyword).enqueue(object : Callback<BlogData> {
            override fun onFailure(call: Call<BlogData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<BlogData>, response: Response<BlogData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let {
                        success(BlogData(it))
                    }
                }
            }
        })
    }

    override fun getKin(keyword: String, success: (KinData) -> Unit, fail: (Throwable) -> Unit) {
        restClient.requestKin(keyword).enqueue(object : Callback<KinData> {
            override fun onFailure(call: Call<KinData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<KinData>, response: Response<KinData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let {
                        success(KinData(it))
                    }
                }
            }
        })
    }

    override fun getImage(keyword: String, success: (ImageData) -> Unit, fail: (Throwable) -> Unit)
    {
        restClient.requestImage(keyword).enqueue(object : Callback<ImageData> {
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                fail(t)
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                if(response.isSuccessful) {
                    response.body()?.items?.let {
                        success(ImageData(it))
                    }
                }
            }
        })
    }
}