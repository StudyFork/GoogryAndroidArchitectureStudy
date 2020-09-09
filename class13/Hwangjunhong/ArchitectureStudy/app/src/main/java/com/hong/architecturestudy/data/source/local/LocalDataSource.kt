package com.hong.architecturestudy.data.source.local

import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface LocalDataSource {
    fun saveResentSearchQuery(keyword: String)
    fun loadResentSearchQuery(): List<MovieInfo>
}