package com.wybh.androidarchitecturestudy.model.repository

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import io.reactivex.Single

interface Repository {
    fun searchCinema(query: String): Single<ResponseCinemaData>
    fun saveSearchWord(word: String)
    fun getSearchWord(): String?
}