package com.deepco.studyfork.data.repository

import com.deepco.studyfork.data.model.Item
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl

class RepositoryMovieDataImpl(
    private val remoteMovieDataImpl: RemoteMovieDataImpl
) : RepositoryMovieData {
    override fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    ) {
        remoteMovieDataImpl.getMovieList(title, {
            success(it)
        }, failed)
    }
}