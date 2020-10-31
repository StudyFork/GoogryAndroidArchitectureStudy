package com.example.myproject.data.source.local

import com.example.myproject.data.sharedPreferences.App
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverLocalDataSourceImpl @Inject constructor() : NaverLocalDataSource {
    override fun saveRecentSearchTitle(title: String) {
        App.prefs.saveRecentSearchTitle(title)
    }

    override fun readRecentSearchTitle(): ArrayList<String> {
        var temp = arrayListOf<String>()
        val dataList = App.prefs.readRecentSearchTitle()
        for (i in dataList.size - 1 downTo 0) {
            temp.add(dataList[i])
        }
        return temp
    }
}
@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: NaverLocalDataSourceImpl): NaverLocalDataSource
}
