package com.example.hw2_project.data.repository

import com.example.hw2_project.data.api.NaverMovieData
import com.example.hw2_project.data.local.MovieLocalDataSourceImpl
import com.example.hw2_project.data.remote.MovieRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class MovieRepositoryImpl @Inject constructor(
    private val remoteMovieDataSourceImp: MovieRemoteDataSourceImpl,
    private val localMovieLocalDataSourceImpl: MovieLocalDataSourceImpl
) : MovieRepository {

    override fun getMovieList(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        this.saveQuery(query)
        remoteMovieDataSourceImp.getMovieFromRemote(
            query,
            success,
            fail
        )
    }

    override fun saveQuery(query: String) {
        localMovieLocalDataSourceImpl.saveQuery(query)
    }

    override fun getSavedQuery(): List<String> {
        return localMovieLocalDataSourceImpl.getSavedQuery()
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