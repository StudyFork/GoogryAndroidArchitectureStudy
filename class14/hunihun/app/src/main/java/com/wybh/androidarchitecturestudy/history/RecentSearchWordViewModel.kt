package com.wybh.androidarchitecturestudy.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wybh.androidarchitecturestudy.base.BaseViewModel
import com.wybh.androidarchitecturestudy.model.local.NaverLocalDataSourceImpl
import com.wybh.androidarchitecturestudy.model.remote.NaverRemoteDataSourceImpl
import com.wybh.androidarchitecturestudy.model.repository.RepositoryImpl

class RecentSearchWordViewModel: BaseViewModel() {
    private val repository: RepositoryImpl by lazy {
        val remoteNaverDataSource = NaverRemoteDataSourceImpl()
        val localNaverDataSource = NaverLocalDataSourceImpl()
        RepositoryImpl(remoteNaverDataSource, localNaverDataSource)
    }

    private val _wordList = MutableLiveData<List<String>>()
    val wordList: LiveData<List<String>> = _wordList

    init {
        getSearchWord()
    }

    private fun getSearchWord() {
        _wordList.value = repository.getSearchWord()!!.split(",")
    }
}