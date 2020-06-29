package com.project.data

import com.project.data.model.MovieItem
import com.project.data.model.NaverMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NaverMovieLocalDataSource {

    fun getMovieList(): Single<NaverMovieEntity>

    fun deleteMovieList()

    fun saveMovieList(data: NaverMovieEntity): Completable
}