package com.project.architecturestudy.data.source.remote

import com.project.architecturestudy.components.NaverApiService
import com.project.architecturestudy.data.model.NaverApiData
import io.reactivex.Single
import org.koin.java.KoinJavaComponent.inject

object NaverMovieRemoteDataSourceImpl : NaverMovieRemoteDataSource {

    override val service: NaverApiService by inject(NaverApiService::class.java)

    override fun getMovieList(keyWord: String): Single<NaverApiData> = service.getMovies(keyWord)
}