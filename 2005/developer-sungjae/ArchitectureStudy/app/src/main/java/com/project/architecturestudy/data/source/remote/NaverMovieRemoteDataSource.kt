package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.NaverApiService
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single

interface NaverMovieRemoteDataSource {

    val service: NaverApiService

    fun getMovieList(keyWord: String): Single<NaverApiData>

}