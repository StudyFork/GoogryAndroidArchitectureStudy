package r.test.rapp.data.repository

import r.test.rapp.data.model.MovieVo

interface MovieRepository {

    fun getMovieList(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

}