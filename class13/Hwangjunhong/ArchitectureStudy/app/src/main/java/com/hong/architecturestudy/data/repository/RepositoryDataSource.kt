package com.hong.architecturestudy.data.repository

import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface RepositoryDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun saveRecentSearchQuery(keyword: String)
    fun loadRecentSearchQuery(): List<MovieInfo>
}