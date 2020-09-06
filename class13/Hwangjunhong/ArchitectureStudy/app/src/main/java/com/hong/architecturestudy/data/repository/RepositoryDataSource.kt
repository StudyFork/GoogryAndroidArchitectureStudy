package com.hong.architecturestudy.data.repository

import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface RepositoryDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun saveData(keyword: String)
    fun loadData(): List<MovieInfo>
}