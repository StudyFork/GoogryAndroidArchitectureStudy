package com.hong.architecturestudy.data.source.local

import com.hong.architecturestudy.MyApplication
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSourceImpl : LocalDataSource {
    private var movieDB: MovieDatabase? =
        MovieDatabase.getInstance(MyApplication.applicationContext())

    override fun saveRecentSearchQuery(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieInfo = MovieInfo()
            movieInfo.movieTitle = keyword
            movieDB?.movieDao()?.insert(movieInfo)
        }
    }

    override fun loadRecentSearchQuery(): List<MovieInfo> {
        return movieDB?.movieDao()?.getAll()!!
    }

}