package com.lllccww.studyforkproject.data.source.remote

import com.lllccww.studyforkproject.data.model.MovieItem

interface MovieRemoteDataSource {

    fun getMovieList(
        keyWord: String,
        onSuccess: (List<MovieItem>) -> Unit,
        onFailure: (e: Throwable) -> Unit
    )


}