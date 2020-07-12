package com.world.tree.architecturestudy.repository.remote

import com.world.tree.architecturestudy.model.Movie

interface NaverRepository {
    fun getMovies(q :String, success:(List<Movie.Item>) -> Unit, error:(Throwable) -> Unit)
}