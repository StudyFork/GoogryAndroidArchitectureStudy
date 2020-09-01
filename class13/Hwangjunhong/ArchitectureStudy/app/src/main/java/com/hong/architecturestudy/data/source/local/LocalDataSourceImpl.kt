package com.hong.architecturestudy.data.source.local

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
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
        lifecycleOwner: LifecycleOwner,
        observer: Observer<List<MovieInfo>>,
        context: Context
    ) {
        movieDB = MovieDatabase.getInstance(context)
        movieDB?.movieDao()?.getAll()?.observe(lifecycleOwner, observer)
    }

}