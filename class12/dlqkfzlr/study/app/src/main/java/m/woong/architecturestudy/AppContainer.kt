package m.woong.architecturestudy

import m.woong.architecturestudy.data.repository.MovieRepositoryImpl
import m.woong.architecturestudy.data.source.remote.MovieApi
import m.woong.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://openapi.naver.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(MovieApi::class.java)
    private val remoteDataSource = MovieRemoteDataSourceImpl(retrofit)
    //private val localDataSource = MovieLocalDataSourceImpl()

    val movieRepository = MovieRepositoryImpl(remoteDataSource)
}