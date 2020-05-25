package com.sangjin.newproject.data.source.remote

import com.sangjin.newproject.data.model.NaverMovieResponse
import io.reactivex.Single

interface RemoteDataSource {

    fun getMovieData(query: String) : Single<NaverMovieResponse>

}