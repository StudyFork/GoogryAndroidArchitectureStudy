package com.example.data.source.local

import com.example.data.model.Movie
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