package com.world.tree.architecturestudy.repository

import com.world.tree.architecturestudy.model.Movie

class NaverRepositoryImpl(val repository: NaverRepository) : NaverRepository {
    override fun getMovies(
        q: String,
        success: (List<Movie.Item>) -> Unit,
        error: (Throwable) -> Unit
    ) {
        repository.getMovies(q, success = success, error = error)
    }

}