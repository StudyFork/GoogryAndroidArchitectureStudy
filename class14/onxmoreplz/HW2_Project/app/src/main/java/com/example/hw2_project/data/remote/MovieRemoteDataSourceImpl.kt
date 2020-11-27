package com.example.hw2_project.data.remote

import com.example.hw2_project.data.api.NaverMovieApi
import com.example.hw2_project.data.api.NaverMovieData
import retrofit2.Call
import retrofit2.Response

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    companion object {
        //Naver 영화 검색 API 사용을 위한 App 등록
        private const val CLIENT_ID = "fQFY7M9rMOVD2KDT8Aaq"
        private const val CLIENT_SECRET = "v8aD8p_Ri0"
    }

    private val naverApi = NaverMovieApi.create()

    override fun getMovieFromRemote(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverApi.searchMovieInApi(query)
            .enqueue(object : retrofit2.Callback<NaverMovieData.NaverMovieResponse> {
                override fun onResponse(
                    call: Call<NaverMovieData.NaverMovieResponse>,
                    response: Response<NaverMovieData.NaverMovieResponse>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            success(it)
                        }
                    }
                }

                override fun onFailure(
                    call: Call<NaverMovieData.NaverMovieResponse>,
                    t: Throwable
                ) {
                    fail(t)
                }

            })
    }
}