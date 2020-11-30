package com.deepco.studyfork.viewmodel

import androidx.databinding.ObservableField
import com.deepco.studyfork.data.local.LocalMovieDataImpl
import com.deepco.studyfork.data.model.Item
import com.deepco.studyfork.data.remote.RemoteMovieDataImpl
import com.deepco.studyfork.data.repository.RepositoryMovieDataImpl

class MainViewModel {
    val query = ObservableField<String>()
    val message = ObservableField<String>()
    val movieList = ObservableField<List<Item>>()

    private val repositoryMovieDataImpl = RepositoryMovieDataImpl(
        RemoteMovieDataImpl(),
        LocalMovieDataImpl()
    )

    fun queryMovie() {
        if (query.get().isNullOrBlank()) {
            message.set("영화 제목을 입력해주세요")
        } else {
            repositoryMovieDataImpl.saveQuery(query.get().toString())
            repositoryMovieDataImpl.getMovieList(query.get().toString(), {
                movieList.set(it)
            }, {
                message.set(it)
            })
        }
    }

}