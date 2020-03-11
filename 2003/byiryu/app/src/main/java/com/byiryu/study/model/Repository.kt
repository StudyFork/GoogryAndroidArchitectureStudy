package com.byiryu.study.model

import com.byiryu.study.model.entity.MovieItem
import com.byiryu.study.model.source.remote.RemoteDataSource
import io.reactivex.Single

class Repository {


    private var remoteDataSource = RemoteDataSource()

    fun getMovieList(
        query: String
    ): Single<List<MovieItem>> {
        return remoteDataSource.getMoveList(query)
            .map { it.items }
    }

}