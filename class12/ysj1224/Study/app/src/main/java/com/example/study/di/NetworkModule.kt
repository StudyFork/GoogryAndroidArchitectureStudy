package com.example.study.di

import com.example.study.data.api.NaverApi
import com.example.study.data.remote.RemoteDataSource
import com.example.study.data.remote.RemoteDataSourceImpl
import com.example.study.data.repository.MovieListRepository
import com.example.study.data.repository.MovieListRepositoryImpl
import com.example.study.view.baseUrl
import com.example.study.viewmodel.MovieViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single{getNaverRetrofit()}
}
fun getNaverRetrofit() : NaverApi{
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(
            NaverApi::class.java)
}