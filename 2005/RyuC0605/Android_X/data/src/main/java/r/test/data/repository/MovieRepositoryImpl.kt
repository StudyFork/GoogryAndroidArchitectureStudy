package r.test.data.repository

import r.test.data.model.MovieVo
import r.test.data.remote.NaverRemoteDataSource
import r.test.data.remote.NaverRemoteDataSourceImpl

class MovieRepositoryImpl(private val naverSource: NaverRemoteDataSource) :
    MovieRepository {
//    val naverSource: NaverRemoteDataSource = NaverRemoteDataSourceImpl()

    override fun getMovieList(
        keyword: String,
        onSuccess: (movies: MovieVo) -> Unit,
        onFail: (t: Throwable) -> Unit
    ) {
        naverSource.getData(keyword, onSuccess, onFail)
    }
}