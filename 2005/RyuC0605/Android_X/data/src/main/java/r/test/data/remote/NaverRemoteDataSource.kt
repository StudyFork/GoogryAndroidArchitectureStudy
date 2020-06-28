package r.test.data.remote

import r.test.data.model.MovieVo

interface NaverRemoteDataSource {

    fun getData(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

}