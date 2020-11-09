package com.wybh.androidarchitecturestudy.data.remote

import com.wybh.androidarchitecturestudy.model.ResponseCinemaData

interface RemoteNaverApi {
    fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    )
}