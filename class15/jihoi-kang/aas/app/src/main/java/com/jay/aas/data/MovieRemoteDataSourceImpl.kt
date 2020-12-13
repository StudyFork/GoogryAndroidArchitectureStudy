package com.jay.aas.data

import com.jay.aas.api.MovieService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService,
) : MovieRemoteDataSource {

    override suspend fun getSearchMovies(query: String) =
        movieService.getMovies(query).items

}

@InstallIn(ApplicationComponent::class)
@Module
abstract class MovieRemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

}