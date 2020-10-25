package com.hjhan.hyejeong.data.source.local

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverLocalDataSourceImpl @Inject constructor() : NaverLocalDataSource {

    override fun saveQuery(query: String) = SharedPreferencesUtil.setQuery(query)

    override fun getQueryList(): List<String> = SharedPreferencesUtil.getQueryList()

}

@Module
@InstallIn(ApplicationComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalDataSource(localDataSourceImpl: NaverLocalDataSourceImpl): NaverLocalDataSource
}