package com.deepco.studyfork.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deepco.data.data.model.Item
import com.deepco.data.data.repository.RepositoryMovieDataImpl

class MainViewModel @ViewModelInject constructor(
    private val repositoryMovieDataImpl: RepositoryMovieDataImpl
) : ViewModel() {
    val query = MutableLiveData<String>()
    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message
    private val _movieList = MutableLiveData<List<Item>>()
    val movieList: LiveData<List<Item>> = _movieList
    private val _startRecentSearchActivity = MutableLiveData<Unit>()
    val startRecentSearchActivity: LiveData<Unit> = _startRecentSearchActivity

    fun queryMovie() {
        if (query.value.isNullOrBlank()) {
            _message.value = "영화 제목을 입력해주세요"
        } else {
            repositoryMovieDataImpl.getMovieList(query.value.toString(), {
                _movieList.value = it
            }, {
                _message.value = it
            })
        }
    }

    fun recentSearch() {
        _startRecentSearchActivity.value = Unit
    }

}