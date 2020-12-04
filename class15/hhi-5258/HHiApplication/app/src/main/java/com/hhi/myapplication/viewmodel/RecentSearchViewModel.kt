package com.hhi.myapplication.viewmodel

import androidx.databinding.ObservableField
import com.hhi.myapplication.base.BaseViewModel
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class RecentSearchViewModel : BaseViewModel() {
    private val naverRepositoryDataSource = NaverRepositoryDataSourceImpl()
    val queryList = ObservableField<List<String>>()

    fun searchRecentQuery() {
        visible.set(false)
        val list = naverRepositoryDataSource.getQueryList()
        queryList.set(list)
        visible.set(false)
    }
}