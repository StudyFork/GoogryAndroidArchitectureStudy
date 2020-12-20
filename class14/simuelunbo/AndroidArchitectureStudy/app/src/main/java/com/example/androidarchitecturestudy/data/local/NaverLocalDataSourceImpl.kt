package com.example.androidarchitecturestudy.data.local

import com.example.androidarchitecturestudy.MyApplication
import com.example.androidarchitecturestudy.Preferences
import com.example.androidarchitecturestudy.data.model.Movie
import com.example.androidarchitecturestudy.data.model.QueryHistory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverLocalDataSourceImpl @Inject constructor(
    private val preferences: Preferences
) : NaverLocalDataSource {
    override fun saveMovieData(movie: List<Movie>) = preferences.saveMovieList(movie)
    override fun getMovieData(): List<Movie>? = preferences.getMovieList()
    override fun saveMovieQuery(title: String) {
        val titleList = getMovieQueryList()
        titleList.add(QueryHistory(title))
        if (titleList.size > 5) {
            titleList.removeAt(0)
        }
        preferences.saveMovieQuery(titleList)
    }

    override fun getMovieQueryList(): ArrayList<QueryHistory> =
        preferences.getMovieQueryList()
}
@Module
@InstallIn(ApplicationComponent::class)
abstract class NaverLocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindNaverLocalDataSource(naverLocalDataSourceImpl: NaverLocalDataSourceImpl): NaverLocalDataSource
}
