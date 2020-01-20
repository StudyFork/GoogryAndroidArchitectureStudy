package com.example.archstudy.data.source.remote

import com.example.archstudy.BuildConfig
import com.example.archstudy.MovieDataResponse
import com.example.archstudy.network.RetrofitHelper
import retrofit2.Call

class NaverQueryRemoteDataSourceImpl : NaverQueryRemoteDataSource {

    override fun getMovie(query: String): Call<MovieDataResponse> {

        return RetrofitHelper
            .getInstance()
            .apiService
            .getMovieList(
                BuildConfig.API_CLIENT_ID,
                BuildConfig.API_CLIENT_SECRET,
                query
            )
    }
}