package com.hong.architecturestudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.repository.RepositoryDataSource

class MainViewModel(private val repositoryDataSource: RepositoryDataSource) : ViewModel() {

    private val _movieList = MutableLiveData<List<MovieData>>()
    private val _msg = MutableLiveData<Message>()
    val query = MutableLiveData<String>()
    val isVisible = MutableLiveData<Boolean>()

    val movieList: LiveData<List<MovieData>> get() = _movieList
    val msg: LiveData<Message> get() = _msg

    fun searchMovieList() {
        val query = query.value ?: return
        repositoryDataSource.getMovieList(query,
            onSuccess = {
                _msg.value = Message.SUCCESS
                _movieList.value = it
            },
            onFailure = {
                _msg.value = Message.NETWORK_ERROR
            })
    }
}

enum class Message {
    NETWORK_ERROR,
    SUCCESS
}