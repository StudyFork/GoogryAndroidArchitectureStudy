package com.sangjin.newproject.data.source.local

import com.sangjin.newproject.data.model.Movie
import io.reactivex.Single

interface LocalDataSource {

    fun getMovieData() : Single<List<Movie>>

    fun saveMovieData(
        movies: List<Movie>
    )

    fun getCacheKeyword() : String

    fun saveCacheKeyword(
        keyword: String
    )

    fun clearCacheKeyword()

}