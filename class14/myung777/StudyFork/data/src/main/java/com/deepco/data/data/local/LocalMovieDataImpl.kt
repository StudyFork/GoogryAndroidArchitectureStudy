package com.deepco.data.data.local

import com.deepco.data.data.model.RecentSearchData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class LocalMovieDataImpl @Inject constructor(
    private val sharedPreferenceUtil: SharedPreferenceUtil
) : LocalMovieData {
    override fun saveQuery(query: String) {
        val queryList = getQueryList().toMutableList()
        queryList.add(RecentSearchData(query))

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }
        saveQueryList(queryList)
    }

    private fun saveQueryList(query: List<RecentSearchData>) {
        sharedPreferenceUtil.saveQuery(query)
    }

    override fun getQueryList(): List<RecentSearchData> =
        sharedPreferenceUtil.getQueryList()
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalMovieDataModule {

    @Binds
    @Singleton
    abstract fun bindLocalMovieData(localMovieDataImpl: LocalMovieDataImpl): LocalMovieData
}