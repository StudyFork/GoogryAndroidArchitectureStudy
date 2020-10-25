package com.hong.data.source.local

import android.content.Context
import com.hong.data.source.local.entity.MovieInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

class LocalDataSourceImpl @Inject constructor(context: Context) : LocalDataSource {
    private var movieDB: MovieDatabase? =
        MovieDatabase.getInstance(context)

    override fun saveRecentSearchQuery(keyword: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val movieInfo = MovieInfo()
            movieInfo.movieTitle = keyword
            movieDB?.movieDao()?.insert(movieInfo)
        }
    }

    override fun loadRecentSearchQuery(): List<MovieInfo> {
        return movieDB?.movieDao()?.getAll()!!
    }

}

@Module
@InstallIn(ApplicationComponent::class)
class LocalDataSourceModule {
    @Provides
    @Singleton
    fun provideLocalDataSource(@ApplicationContext applicationContext: Context): LocalDataSource {
        return LocalDataSourceImpl(applicationContext)
    }

}
