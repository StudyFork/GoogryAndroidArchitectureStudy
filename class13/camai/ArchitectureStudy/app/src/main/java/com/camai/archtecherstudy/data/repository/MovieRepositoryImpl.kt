package com.camai.archtecherstudy.data.repository

import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.source.local.MovieLocalDataSource
import com.camai.archtecherstudy.data.source.local.room.RecentSearchName
import com.camai.archtecherstudy.data.source.remote.MovieRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton


class MovieRepositoryImpl @Inject constructor(
    private val localMovieRepository: MovieLocalDataSource,
    private val remoteMovieRepository: MovieRemoteDataSource
) : MovieRepository {

    override fun getMovieNameSearch(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        //  Data Insert
        setMovieNameInsert(keyword)
        //  retrofit call
        remoteMovieRepository.getSearchMovie(keyword, display, start, success, failed)
    }

    override fun getRecentSearchList(
        namelist: (List<RecentSearchName>) -> Unit
    ) {
        localMovieRepository.getRecentMovieNameList(namelist)
    }


    override fun setMovieNameInsert(keyword: String) {
        localMovieRepository.saveMovieName(keyword)
    }

    override fun deteleDb() {
        localMovieRepository.deleteDb()
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieRepositoryModule {
    @Binds
    @Singleton
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository
}
