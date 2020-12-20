package com.example.hw2_project.data.local

import com.example.hw2_project.App
import com.example.hw2_project.SharedPreferenceUtil
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import org.json.JSONArray
import javax.inject.Inject
import javax.inject.Singleton

class MovieLocalDataSourceImpl @Inject constructor(
    private val sharedPreferenceUtil: SharedPreferenceUtil
) : MovieLocalDataSource {
    override fun saveQuery(query: String) {
        val queryList = getSavedQuery().toMutableList()
        queryList.add(query)

        if (queryList.size > 5) {
            queryList.removeAt(0)
        }
        sharedPreferenceUtil.saveQueryList(queryList)
    }

    override fun getSavedQuery(): List<String> {
        val json = sharedPreferenceUtil.getSavedQueryListTest()
        val queryList = mutableListOf<String>()

        json?.let {
            val jsonArray = JSONArray(it)
            for (i in 0 until jsonArray.length()) {
                queryList.add(jsonArray[i].toString())
            }
        }
        return queryList
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class MovieLocalModule {

    @Binds
    @Singleton
    abstract fun bindMovieLocalDataSource(
        localDataSourceImpl: MovieLocalDataSourceImpl
    ): MovieLocalDataSource
}