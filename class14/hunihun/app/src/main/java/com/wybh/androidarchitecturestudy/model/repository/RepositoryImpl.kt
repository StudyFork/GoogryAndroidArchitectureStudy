package com.wybh.androidarchitecturestudy.model.repository

import com.wybh.androidarchitecturestudy.data.ResponseCinemaData
import com.wybh.androidarchitecturestudy.model.local.NaverLocalDataSourceImpl
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import io.reactivex.Single

class RepositoryImpl(
    private val naverRemoteDataSource: NaverRemoteDataSourceImpl,
    private val naverLocalDataSource: NaverLocalDataSourceImpl
) : Repository {

    override fun searchCinema(query: String): Single<ResponseCinemaData> {
        return naverRemoteDataSource.searchCinema(query)
    }

    override fun saveSearchWord(word: String) {
        return naverLocalDataSource.saveSearchWord(word)
    }

    override fun getSearchWord(): String? {
        return naverLocalDataSource.getSearchWord()
    }
}