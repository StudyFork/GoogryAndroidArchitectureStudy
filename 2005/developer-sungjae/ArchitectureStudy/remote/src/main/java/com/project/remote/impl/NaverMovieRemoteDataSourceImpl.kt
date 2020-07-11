package com.project.remote.impl

import com.project.data.NaverMovieRemoteDataSource
import com.project.data.model.NaverMovieEntity
import com.project.remote.NaverApiService
import com.project.remote.response.mapToNaverMovieEntity
import io.reactivex.Single

class NaverMovieRemoteDataSourceImpl(private val service: NaverApiService) : NaverMovieRemoteDataSource {

    override fun getMovieList(keyWord: String): Single<NaverMovieEntity> = service.getMovies(keyWord).map { it.mapToNaverMovieEntity() }
}