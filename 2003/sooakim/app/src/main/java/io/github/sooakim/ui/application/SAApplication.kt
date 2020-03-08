package io.github.sooakim.ui.application

import android.app.Application
import io.github.sooakim.data.local.SADatabase
import io.github.sooakim.data.local.mapper.SAMovieLocalMapper
import io.github.sooakim.data.local.pref.SAPreferencesHelper
import io.github.sooakim.data.local.pref.SAPreferencesHelperImpl
import io.github.sooakim.data.local.source.SAMovieLocalDataSourceImpl
import io.github.sooakim.data.remote.SANetworkService
import io.github.sooakim.data.remote.SANetworkServiceImpl
import io.github.sooakim.data.remote.mapper.SAMovieRemoteMapper
import io.github.sooakim.data.remote.source.SAMovieRemoteDataSourceImpl
import io.github.sooakim.domain.repository.SAMovieRepository
import io.github.sooakim.domain.repository.SAMovieRepositoryImpl
import io.github.sooakim.ui.movie.mapper.SAMovieMapper
import io.github.sooakim.domain.mapper.SAMovieMapper as SAMovieDomainMapper

class SAApplication : Application() {
    lateinit var preferencesHelper: SAPreferencesHelper
    lateinit var movieRepository: SAMovieRepository
    lateinit var movieMapper: SAMovieMapper
    lateinit var networkService: SANetworkService

    override fun onCreate() {
        super.onCreate()
        inject()
    }

    private fun inject() {
        preferencesHelper = SAPreferencesHelperImpl(applicationContext)


        val database = SADatabase.Factory.create(applicationContext)
        val movieLocalMapper = SAMovieLocalMapper()
        val movieLocalDataSource = SAMovieLocalDataSourceImpl(database.movieDao, movieLocalMapper)

        networkService = SANetworkServiceImpl()
        val movieRemoteMapper = SAMovieRemoteMapper()
        val movieRemoteDataSource =
            SAMovieRemoteDataSourceImpl(networkService.movieApi, movieRemoteMapper)

        val movieDomainMapper = SAMovieDomainMapper()
        movieRepository = SAMovieRepositoryImpl(
            movieLocalDataSource,
            movieRemoteDataSource,
            movieDomainMapper
        )

        movieMapper = SAMovieMapper()
    }
}