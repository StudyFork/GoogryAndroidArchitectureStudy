package com.example.data.source.remote

import com.example.data.model.NaverMovieResponse
import io.reactivex.Single

interface RemoteDataSource {

    fun getMovieData(query: String) : Single<NaverMovieResponse>

}