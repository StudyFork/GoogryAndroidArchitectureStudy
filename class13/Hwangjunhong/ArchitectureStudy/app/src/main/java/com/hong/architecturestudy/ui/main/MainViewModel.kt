package com.hong.architecturestudy.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hong.architecturestudy.ui.base.BaseViewModel
import com.hong.data.model.MovieData
import com.hong.data.repository.RepositoryDataSource


class MainViewModel @ViewModelInject constructor(
    private val repositoryDataSource: RepositoryDataSource
) : BaseViewModel() {

    private val _movieList = MutableLiveData<List<MovieData>>()
    val movieList: LiveData<List<MovieData>> get() = _movieList

    private val _isVisibleDialog = MutableLiveData<Boolean>()
    val isVisibleDialog: LiveData<Boolean> get() = _isVisibleDialog

    val query = MutableLiveData<String>()


    fun searchMovieList(query: String) {
        repositoryDataSource.getMovieList(query,
            onSuccess = {
                _msg.value = Message.SUCCESS
                _movieList.value = it
            },
            onFailure = {
                _msg.value = Message.NETWORK_ERROR
            })
    }

    fun isVisible(boolean: Boolean) {
        _isVisibleDialog.value = boolean
    }
}

enum class Message {
    NETWORK_ERROR,
    SUCCESS
}