package com.example.hw2_project.data.remote

import android.app.Application
import com.example.hw2_project.data.api.NaverMovieApi
import com.example.hw2_project.data.api.NaverMovieData
import com.example.hw2_project.di.NaverMovieApiModule
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MovieRemoteDataSourceImpl @Inject constructor(
    private val naverMovieApiModule: NaverMovieApiModule
) : MovieRemoteDataSource {

    override fun getMovieFromRemote(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverMovieApiModule.create().searchMovieInApi(query)
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

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieRemoteModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(
        remoteDataSourceImpl: MovieRemoteDataSourceImpl
    ): MovieRemoteDataSource
}