package com.project.data

import com.project.data.model.MovieItem
import com.project.data.model.NaverMovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NaverMovieRepository {

    fun getMovieList(keyWord: String): Single<NaverMovieEntity>

    fun getCashedMovieList(): Single<NaverMovieEntity>

    fun saveMovieList(movieList: NaverMovieEntity): Completable

    fun deleteMovieList()
}