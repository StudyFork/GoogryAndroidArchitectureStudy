package com.world.tree.architecturestudy.repository.remote

import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.source.remote.NaverRemoteDataSource
import com.world.tree.architecturestudy.source.remote.NaverRemoteDataSourceImpl

class NaverRepositoryImpl(private val remoteDataSource: NaverRemoteDataSource) :
    NaverRepository {
    override fun getMovies(
        q: String,
        success: (List<Movie.Item>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovies(q, success = success, error = error)
    }

}