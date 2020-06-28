package r.test.data

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import r.test.data.networks.NaverApi
import r.test.data.networks.RetrofitClient
import r.test.data.repository.MovieRepository
import r.test.data.repository.MovieRepositoryImpl
import r.test.data.remote.NaverRemoteDataSource
import r.test.data.remote.NaverRemoteDataSourceImpl

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

    viewModel { MainViewModel(get(), get()) }
}

fun genRetrofitApi(): NaverApi {
    return RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)
}