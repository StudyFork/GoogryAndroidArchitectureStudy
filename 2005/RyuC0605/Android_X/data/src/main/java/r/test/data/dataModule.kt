package r.test.data

import org.koin.dsl.module
import r.test.data.networks.NaverApi
import r.test.data.networks.RetrofitClient
import r.test.data.remote.NaverRemoteDataSource
import r.test.data.remote.NaverRemoteDataSourceImpl
import r.test.data.repository.MovieRepository
import r.test.data.repository.MovieRepositoryImpl

val dataModule = module {

    single<NaverApi> { genRetrofitApi() }

    single<NaverRemoteDataSource> {
        NaverRemoteDataSourceImpl(
            get()
        )
    }

    single<MovieRepository> {
        MovieRepositoryImpl(
            get()
        )
    }
}

fun genRetrofitApi(): NaverApi {
    return RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)
}