package com.example.androidarchitecturestudy.data.remote

import android.app.Application
import com.example.androidarchitecturestudy.data.api.NaverMovieInterface
import com.example.androidarchitecturestudy.data.api.RetrofitClient
import com.example.androidarchitecturestudy.data.model.MovieData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class NaverRemoteDataSourceImpl @Inject constructor(
    private val retrofitClient: RetrofitClient
): NaverRemoteDataSource {

    override fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        val api = retrofitClient.naverMovieInterface(NaverMovieInterface::class.java)
        api.searchMovies(query).enqueue(object :
            retrofit2.Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it)
                    }
                } else {
                    failed(response.message())
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                failed("다시 검색해 주세요")
            }

        })
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class NaverRemoteDataSourceModule{

    @Binds
    @Singleton
    abstract fun bindNaverRemoteDataSource(naverRemoteDataSourceImpl: NaverRemoteDataSourceImpl): NaverRemoteDataSource
}