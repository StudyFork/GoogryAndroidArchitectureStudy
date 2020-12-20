package com.example.hw2_project.data.repository

import com.example.hw2_project.data.api.NaverMovieData
import com.example.hw2_project.data.local.MovieLocalDataSource
import com.example.hw2_project.data.remote.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepositoryImpl @Inject constructor(
    private val remoteMovieDataSource: MovieRemoteDataSource,
    private val localMovieLocalDataSource: MovieLocalDataSource
) : MovieRepository {

    override fun getMovieList(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        this.saveQuery(query)
        remoteMovieDataSource.getMovieFromRemote(
            query,
            success,
            fail
        )
    }

    override fun saveQuery(query: String) {
        localMovieLocalDataSource.saveQuery(query)
    }

    override fun getSavedQuery(): List<String> {
        return localMovieLocalDataSource.getSavedQuery()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}