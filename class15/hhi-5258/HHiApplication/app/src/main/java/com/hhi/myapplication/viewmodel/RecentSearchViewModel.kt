package com.hhi.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hhi.myapplication.base.BaseViewModel
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class RecentSearchViewModel : BaseViewModel() {
    private val naverRepositoryDataSource = NaverRepositoryDataSourceImpl()
    private val _queryList = MutableLiveData<List<String>>()
    val queryList: LiveData<List<String>> = _queryList

    fun searchRecentQuery() {
        visible.value = false
        val list = naverRepositoryDataSource.getQueryList()
        _queryList.value = list
        visible.value = false
    }
}