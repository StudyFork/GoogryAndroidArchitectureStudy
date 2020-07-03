package r.test.data.repository

import r.test.data.model.MovieVo

interface MovieRepository {

    fun getMovieList(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

}