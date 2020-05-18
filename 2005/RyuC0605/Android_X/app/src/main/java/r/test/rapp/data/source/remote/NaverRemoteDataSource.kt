package r.test.rapp.data.source.remote

import r.test.rapp.data.model.MovieVo

interface NaverRemoteDataSource {

    fun getMovie(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

}