package app.ch.study.data.repository

import app.ch.study.data.local.source.NaverQueryLocalDataSource
import app.ch.study.data.remote.response.MovieModel
import app.ch.study.data.remote.source.NaverQueryRemoteDataSource
import io.reactivex.Single

class NaverQueryRepositoryImple(
    naverQueryLocalDataSource: NaverQueryLocalDataSource,
    naverQueryRemoteDataSource: NaverQueryRemoteDataSource
) : NaverQueryRepository {

    override fun searchMovie(): Single<MutableList<MovieModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}