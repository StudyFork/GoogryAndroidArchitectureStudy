package com.tsdev.tsandroid.data.repository

import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.MovieResponse
import io.reactivex.rxjava3.core.Single

interface NaverReopsitory {
        fun getMovieList(query: String): Single<List<Item>>
//    fun getMovieList(query: String): Single<MovieResponse>
}