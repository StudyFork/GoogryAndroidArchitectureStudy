package com.world.tree.architecturestudy

import com.world.tree.architecturestudy.model.BASE_URL
import com.world.tree.architecturestudy.model.MovieApi
import com.world.tree.architecturestudy.model.repository.remote.NaverRepositoryImpl
import com.world.tree.architecturestudy.model.source.remote.NaverRemoteDataSourceImpl
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