package com.wybh.androidarchitecturestudy.model.remote

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import io.reactivex.Single

interface NaverRemoteDataSource {
    fun searchCinema(query: String): Single<ResponseCinemaData>
}