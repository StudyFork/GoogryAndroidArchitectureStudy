package com.hong.architecturestudy.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LocalDataSourceImpl : LocalDataSource {
    private var movieDB: MovieDatabase? = null

    override fun saveData(keyword: String, context: Context) {
        movieDB = MovieDatabase.getInstance(context)
        CoroutineScope(Dispatchers.IO).launch {
            val movieInfo = MovieInfo()
            movieInfo.movieTitle = keyword
            movieDB?.movieDao()?.insert(movieInfo)
        }
    }

    override fun loadData(
        context: Context
    ): LiveData<List<MovieInfo>> {
        movieDB = MovieDatabase.getInstance(context)
        return movieDB?.movieDao()?.getAll()!!
    }

}