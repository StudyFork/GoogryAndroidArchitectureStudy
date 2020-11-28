package com.showmiso.architecturestudy.data.repository

import com.showmiso.architecturestudy.api.MovieModel
import io.reactivex.Single

interface NaverRepository {

    fun getMoviesList(query: String): Single<List<MovieModel.Movie>>

    fun addHistory(query: String)

    fun getHistory(): List<String>?

    fun removeHistory(query: String)

    fun removeAllHistory()
}