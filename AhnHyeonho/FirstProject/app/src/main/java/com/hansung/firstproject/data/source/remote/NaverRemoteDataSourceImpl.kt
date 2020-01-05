package com.hansung.firstproject.data.source.remote

import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.source.remote.api.NaverApiServiceImpl
import retrofit2.Call
import retrofit2.Response


class NaverRemoteDataSourceImpl private constructor() : NaverRemoteDataSource {
    override fun getMoviesData(
        title: String,
        clientId: String,
        clientSecret: String,
        onResponse: (MovieResponseModel) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        NaverApiServiceImpl.getResult(clientId, clientSecret, title, 100)
            .enqueue(object : retrofit2.Callback<MovieResponseModel> {
                override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<MovieResponseModel>,
                    response: Response<MovieResponseModel>
                ) {
                    if (response.isSuccessful) {
                        onResponse(response.body()!!)
                    }
                }
            })
    }

    companion object {
        @Volatile
        private var _INSTANCE: NaverRemoteDataSourceImpl? = null

        @JvmStatic
        fun getInstance(): NaverRemoteDataSourceImpl =
            _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: NaverRemoteDataSourceImpl().also {
                    _INSTANCE = it
                }
            }
    }
}