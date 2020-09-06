package com.hong.architecturestudy.data.source.local

import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface LocalDataSource {
    fun saveData(keyword: String)
    fun loadData(): List<MovieInfo>
}