package com.world.tree.architecturestudy.source

import com.world.tree.architecturestudy.model.Movie

interface NaverRemoteDataSource {
    fun getMovies(q : String, success:(List<Movie.Item>) -> Unit,
    error: (Throwable) -> Unit)
}