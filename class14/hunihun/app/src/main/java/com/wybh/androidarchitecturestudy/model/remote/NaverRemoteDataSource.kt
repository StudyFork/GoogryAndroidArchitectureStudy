package com.wybh.androidarchitecturestudy.model.remote

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData

interface NaverRemoteDataSource {
    fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    )
}