package com.hong.data.repository

import com.hong.data.source.local.entity.MovieInfo
import com.hong.data.model.MovieData

interface RepositoryDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun saveRecentSearchQuery(keyword: String)
    fun loadRecentSearchQuery(): List<MovieInfo>
}