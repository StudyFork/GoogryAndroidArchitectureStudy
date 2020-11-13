package com.wybh.androidarchitecturestudy.data.repository

import com.wybh.androidarchitecturestudy.model.ResponseCinemaData

interface Repository {
    fun searchCinema(
        query: String,
        success: (ResponseCinemaData) -> Unit,
        fail: (Throwable) -> Unit
    )
}