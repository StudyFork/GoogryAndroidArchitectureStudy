package com.project.data

import com.project.data.model.NaverMovieEntity
import io.reactivex.Single

interface NaverMovieRemoteDataSource {

    fun getMovieList(keyWord: String): Single<NaverMovieEntity>

}