package r.test.data.remote

import r.test.data.model.MovieVo

internal interface NaverRemoteDataSource {

    fun getData(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    )

}