package m.woong.architecturestudy.di

import m.woong.architecturestudy.MovieViewModel
import m.woong.architecturestudy.data.repository.MovieRepository
import m.woong.architecturestudy.data.repository.MovieRepositoryImpl
import m.woong.architecturestudy.data.source.remote.MovieApi
import m.woong.architecturestudy.data.source.remote.MovieRemoteDataSource
import m.woong.architecturestudy.data.source.remote.MovieRemoteDataSourceImpl
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val movieModule = module {

    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(get()) }
    single<MovieRepository> { MovieRepositoryImpl(get()) }

    viewModel { MovieViewModel(get()) }
}