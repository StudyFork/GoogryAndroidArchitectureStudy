package com.hong.architecturestudy.data.source.local

import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface LocalDataSource {
    fun saveRecentSearchQuery(keyword: String)
    fun loadRecentSearchQuery(): List<MovieInfo>
}