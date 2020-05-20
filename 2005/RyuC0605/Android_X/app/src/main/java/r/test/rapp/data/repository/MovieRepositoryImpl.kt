package r.test.rapp.data.repository

import r.test.rapp.data.model.MovieVo
import r.test.rapp.data.source.remote.NaverRemoteDataSource
import r.test.rapp.data.source.remote.NaverRemoteDataSourceImpl

class MovieRepositoryImpl : MovieRepository {
    override fun getMovieList(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    ) {
        val naver: NaverRemoteDataSource = NaverRemoteDataSourceImpl()
        naver.getData(keyword, onSuccess, onFail)
    }
}