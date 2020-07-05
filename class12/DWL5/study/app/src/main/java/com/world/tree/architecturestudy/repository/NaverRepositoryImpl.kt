package com.world.tree.architecturestudy.repository

import com.world.tree.architecturestudy.model.Movie

class NaverRepositoryImpl(val remoteDataSource: NaverRepositoryImpl) : NaverRepository {
    override fun getMovies(
        q: String,
        success: (List<Movie.Item>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovies(q, success = success, error = error)
    }

}