package io.github.sooakim.ui.application

import android.app.Application
import io.github.sooakim.data.SAAuthRepositoryImpl
import io.github.sooakim.data.SAMovieRepositoryImpl
import io.github.sooakim.data.local.SADatabase
import io.github.sooakim.data.local.mapper.SAMovieLocalMapper
import io.github.sooakim.data.local.pref.SAPreferencesHelperImpl
import io.github.sooakim.data.local.source.SAAuthLocalDataSourceImpl
import io.github.sooakim.data.local.source.SAMovieLocalDataSourceImpl
import io.github.sooakim.data.remote.SANetworkServiceImpl
import io.github.sooakim.data.remote.mapper.SAMovieRemoteMapper
import io.github.sooakim.data.remote.source.SAAuthRemoteDataSourceImpl
import io.github.sooakim.data.remote.source.SAMovieRemoteDataSourceImpl
import io.github.sooakim.domain.repository.SAAuthRepository
import io.github.sooakim.domain.repository.SAMovieRepository
import io.github.sooakim.ui.movie.mapper.SAMovieMapper
import io.github.sooakim.data.mapper.SAMovieMapper as SAMovieDomainMapper

class SAApplication : Application() {
    lateinit var authRepository: SAAuthRepository

    lateinit var movieRepository: SAMovieRepository
    lateinit var movieMapper: SAMovieMapper

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        val prefHelper = SAPreferencesHelperImpl(applicationContext)
        val database = SADatabase.Factory.create(applicationContext)
        val networkService = SANetworkServiceImpl()

        val authLocalDataSource = SAAuthLocalDataSourceImpl(prefHelper)
        val authRemoteDataSource = SAAuthRemoteDataSourceImpl(networkService.authApi)
        authRepository = SAAuthRepositoryImpl(
            authLocalDataSource,
            authRemoteDataSource
        )

        val movieLocalMapper = SAMovieLocalMapper()
        val movieLocalDataSource = SAMovieLocalDataSourceImpl(
            prefHelper,
            database.movieDao,
            movieLocalMapper
        )

        val movieRemoteMapper = SAMovieRemoteMapper()
        val movieRemoteDataSource = SAMovieRemoteDataSourceImpl(
            networkService.movieApi,
            movieRemoteMapper
        )

        val movieDomainMapper = SAMovieDomainMapper()
        movieRepository = SAMovieRepositoryImpl(
            movieLocalDataSource,
            movieRemoteDataSource,
            movieDomainMapper
        )

        movieMapper = SAMovieMapper()
    }
}