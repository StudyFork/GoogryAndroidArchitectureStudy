package m.woong.architecturestudy.data.repository

import m.woong.architecturestudy.data.source.remote.NaverRemoteDataSource
import m.woong.architecturestudy.data.source.remote.model.MovieResponse

class NaverRepositoryImpl(
    //private val localDataSource: NaverLocalDataSource,
    private val remoteDataSource: NaverRemoteDataSource
) : NaverRepository {
    
    override fun getRecentMovie(
        query: String,
        success: (MovieResponse) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        getMovie(query, success, failure)
        /*   if (isRecentMovie()){
               getCachedMovie()
           } else {
               getMovie(query)
           }
         */
    }

    override fun getMovie(
        query: String,
        success: (MovieResponse) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        remoteDataSource.getMovie(query, success, failure)
        //val movieList: List<MovieResponse.Item> = remoteDataSource.getMovie(query, success, failure)
        //localDataSource.saveMovie(movieList)
    }

    override fun getCachedMovie() {
        //localDataSource.getMovie()
    }

    override fun isRecentMovie(): Boolean {
        TODO("Not yet implemented")
    }
}