package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single

interface NaverMovieRemoteDataSource {

    fun getMovieList(keyWord: String): Single<NaverApiData>

}