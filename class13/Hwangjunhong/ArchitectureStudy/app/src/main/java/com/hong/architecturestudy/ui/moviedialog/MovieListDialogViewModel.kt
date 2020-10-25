package com.hong.architecturestudy.ui.moviedialog

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hong.architecturestudy.data.repository.RepositoryDataSource
import com.hong.architecturestudy.data.source.local.entity.MovieInfo
import kotlinx.coroutines.*

class MovieListDialogViewModel @ViewModelInject constructor(
    private val repository: RepositoryDataSource
) : ViewModel() {

    private val _movieInfo = MutableLiveData<List<MovieInfo>>()
    val movieInfo: LiveData<List<MovieInfo>> get() = _movieInfo

    private var loadRecentJob: Job? = null

    fun showRecentSearchMovieList() {
        loadRecentJob = CoroutineScope(Dispatchers.IO).launch {
            val list = repository.loadRecentSearchQuery()

            withContext(Dispatchers.Main) {
                _movieInfo.value = list
            }
        }
    }

    override fun onCleared() {
        loadRecentJob?.cancel()
        super.onCleared()
    }

}