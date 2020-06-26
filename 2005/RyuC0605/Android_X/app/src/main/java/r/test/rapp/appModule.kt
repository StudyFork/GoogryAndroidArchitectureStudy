package r.test.rapp

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl
import r.test.rapp.data.source.remote.NaverRemoteDataSource
import r.test.rapp.data.source.remote.NaverRemoteDataSourceImpl
import r.test.rapp.main.MainViewModel
import r.test.rapp.networks.NaverApi
import r.test.rapp.networks.RetrofitClient

val appModule = module {

    single<NaverApi> { genRetrofitApi() }

    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl(get()) }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MainViewModel(get(), get()) }
}

fun genRetrofitApi(): NaverApi {
    return RetrofitClient.getClient(BuildConfig.NAVER_API_URL).create(NaverApi::class.java)
}