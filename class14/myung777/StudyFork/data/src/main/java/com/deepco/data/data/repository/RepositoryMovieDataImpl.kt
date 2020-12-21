package com.deepco.data.data.repository

import com.deepco.data.data.local.LocalMovieDataImpl
import com.deepco.data.data.model.Item
import com.deepco.data.data.model.RecentSearchData
import com.deepco.data.data.remote.RemoteMovieDataImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class RepositoryMovieDataImpl @Inject constructor(
    private val remoteMovieDataImpl: RemoteMovieDataImpl,
    private val localMovieDataImpl: LocalMovieDataImpl

) : RepositoryMovieData {
    override fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    ) {
        remoteMovieDataImpl.getMovieList(
            title, {
                success(it)
                saveQuery(title)
            }, failed
        )
    }

    override fun getQueryList(): List<RecentSearchData> {
        return localMovieDataImpl.getQueryList()
    }

    private fun saveQuery(query: String) {
        localMovieDataImpl.saveQuery(query)
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryMovieDataModule {

    @Binds
    @Singleton
    abstract fun bindRepositoryMovieData(repositoryMovieDataImpl: RepositoryMovieDataImpl): RepositoryMovieData
}