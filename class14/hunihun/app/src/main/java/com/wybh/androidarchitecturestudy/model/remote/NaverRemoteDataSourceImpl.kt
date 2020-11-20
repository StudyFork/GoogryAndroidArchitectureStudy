package com.wybh.androidarchitecturestudy.model.remote

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.retrofit.RetrofitCreator
import com.wybh.androidarchitecturestudy.retrofit.RetrofitImpl
import io.reactivex.Single

class NaverRemoteDataSourceImpl : NaverRemoteDataSource {

    override fun searchCinema(query: String): Single<ResponseCinemaData> {
        return RetrofitCreator.create(RetrofitImpl::class.java)
            .getCinemaData(query)
    }
}