package com.example.studyforkandroid.data.source

import com.example.studyforkandroid.data.Movie
import com.example.studyforkandroid.data.source.local.MovieLocalDataSourceImpl
import com.example.studyforkandroid.data.source.remote.MovieRemoteDataSourceImpl

object MovieRepositoryImpl : MovieRepository {

    // 분기를 어떻게 하면 좋을지 잘 모르겠어서 Local에 대한 데이터는 아직 다루지 않았습니다.
    private val movieLocalDataSource = MovieLocalDataSourceImpl
    private val isCacheDirty = false

    private val movieRemoteDataSource = MovieRemoteDataSourceImpl

    override fun getMovieList(
        query: String,
        onSuccess: (List<Movie>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        if (isCacheDirty) { // 현재는 항상 false
            movieLocalDataSource.getMovieList()
        } else {
            movieRemoteDataSource.getMovieList(query, onSuccess, onFailure)
        }
    }

}