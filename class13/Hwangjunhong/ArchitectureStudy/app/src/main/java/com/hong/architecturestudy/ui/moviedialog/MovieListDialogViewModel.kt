package com.hong.architecturestudy.ui.moviedialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MovieListDialogViewModel(private val repository: RepositoryDataSource) : ViewModel() {

    private val _movieInfo = MutableLiveData<List<MovieInfo>>()
    val movieInfo: LiveData<List<MovieInfo>> get() = _movieInfo

    private var loadRecentJob: Job? = null

    fun loadRecentSearchMovieList() {
        val list = repository.loadRecentSearchQuery()

        loadRecentJob = CoroutineScope(Dispatchers.Main).launch {
            _movieInfo.value = list
        }
    }

    override fun onCleared() {
        loadRecentJob?.cancel()
        super.onCleared()

    }
}