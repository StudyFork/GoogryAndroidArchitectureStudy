package com.world.tree

import com.world.tree.architecturestudy.BASE_URL
import com.world.tree.architecturestudy.MovieApi
import com.world.tree.architecturestudy.repository.remote.NaverRepositoryImpl
import com.world.tree.architecturestudy.source.remote.NaverRemoteDataSource
import com.world.tree.architecturestudy.source.remote.NaverRemoteDataSourceImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieContainer () {
    private val movieService =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)

    private val remoteDataSource = NaverRemoteDataSourceImpl(movieService)
    val repository = NaverRepositoryImpl(remoteDataSource)

}