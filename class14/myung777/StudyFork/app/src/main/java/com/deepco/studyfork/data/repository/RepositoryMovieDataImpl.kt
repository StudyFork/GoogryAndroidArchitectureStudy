package com.deepco.studyfork.data.repository

import com.deepco.studyfork.data.local.LocalMovieDataImpl
import com.deepco.studyfork.data.model.Item
import com.deepco.studyfork.data.model.RecentSearchData
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl

class RepositoryMovieDataImpl(
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