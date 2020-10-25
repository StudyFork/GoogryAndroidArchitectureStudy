package com.hong.data.source.local

import com.hong.data.source.local.entity.MovieInfo

interface LocalDataSource {
    fun saveRecentSearchQuery(keyword: String)
    fun loadRecentSearchQuery(): List<MovieInfo>
}