package com.project.architecturestudy.data.source.local

import com.project.architecturestudy.data.model.Movie

class NaverMovieLocalDataSourceImpl : NaverMovieLocalDataSource {
    override fun getMovieList(
        query: String,
        Success: (Movie.Items) -> Unit,
        Failure: (Throwable) -> Unit
    ) {
    }

    override fun saveMovieList(query: String) {
    }

}