package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.NaverApiService
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single

class NaverMovieRemoteDataSourceImpl(private val service: NaverApiService) : NaverMovieRemoteDataSource {

    override fun getMovieList(keyWord: String): Single<NaverApiData> = service.getMovies(keyWord)
}