package com.example.dkarch.domain.repositoryImpl

import com.example.dkarch.domain.globalconsts.Consts
import com.example.dkarch.domain.repository.HttpClientRepository
import com.example.dkarch.domain.repository.RetrofitRepository
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRepositoryImpl(private val okHttpRepo: HttpClientRepository) :
    RetrofitRepository {

    override fun getAccessRetrofit(): Retrofit {
        val client = okHttpRepo.getAccessOkHttp()
        val baseUrl: String = Consts.BASE_URL

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

}
