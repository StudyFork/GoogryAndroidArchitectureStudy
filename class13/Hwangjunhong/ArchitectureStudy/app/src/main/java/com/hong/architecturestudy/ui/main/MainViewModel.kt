package com.hong.architecturestudy.ui.main

import androidx.databinding.ObservableField
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.repository.RepositoryDataSource

class MainViewModel(private val repositoryDataSource: RepositoryDataSource) {

    val movieList = ObservableField<List<MovieData>>()
    val msg = ObservableField<Message>()
    val query = ObservableField<String>()
    val isVisible = ObservableField<Boolean>()

    fun searchMovieList() {
        val query = query.get() ?: return
        repositoryDataSource.getMovieList(query,
            onSuccess = {
                msg.set(Message.SUCCESS)
                movieList.set(it)
            },
            onFailure = {
                msg.set(Message.NETWORK_ERROR)
            })
    }

    fun visibleChange() {
        isVisible.notifyChange()
    }
}

enum class Message {
    NETWORK_ERROR,
    SUCCESS
}