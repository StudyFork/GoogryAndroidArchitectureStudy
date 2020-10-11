package com.hong.architecturestudy.ui.moviedialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

class MovieListDialogViewModel(private val repository: RepositoryDataSource) : ViewModel() {

    private val _movieInfo = MutableLiveData<List<MovieInfo>>()
    val movieInfo: LiveData<List<MovieInfo>> get() = _movieInfo

    fun loadRecentSearchMovieList() {
        val list = repository.loadRecentSearchQuery()
        _movieInfo.postValue(list)
    }
}