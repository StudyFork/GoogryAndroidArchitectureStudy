package r.test.rapp

import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import r.test.rapp.data.repository.MovieRepository
import r.test.rapp.data.repository.MovieRepositoryImpl
import r.test.rapp.data.source.remote.NaverRemoteDataSource
import r.test.rapp.data.source.remote.NaverRemoteDataSourceImpl
import r.test.rapp.main.MainViewModel

val appModule = module {

    single<NaverRemoteDataSource> { NaverRemoteDataSourceImpl() }

    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MainViewModel(get(), get()) }

}