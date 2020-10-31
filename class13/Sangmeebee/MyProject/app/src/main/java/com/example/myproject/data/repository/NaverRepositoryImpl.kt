package com.example.myproject.data.repository

import com.example.myproject.data.model.Items
import com.example.myproject.data.source.local.NaverLocalDataSource
import com.example.myproject.data.source.remote.NaverRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject
import javax.inject.Singleton

class NaverRepositoryImpl @Inject constructor(
    private val naverLocalDataSource: NaverLocalDataSource,
    private val naverRemoteDataSource: NaverRemoteDataSource
) : NaverRepository {
    override fun getMovieList(
        title: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        naverRemoteDataSource.getMovieList(title, {
            success(it)
            saveRecentSearchTitle(title)
        }, failed)
    }

    override fun saveRecentSearchTitle(title: String) {
        naverLocalDataSource.saveRecentSearchTitle(title)
    }

    override fun readRecentSearchTitle() = naverLocalDataSource.readRecentSearchTitle()
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRepository(repositoryImpl: NaverRepositoryImpl): NaverRepository
}
