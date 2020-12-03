package com.hhi.myapplication.viewmodel

import android.view.View
import androidx.databinding.ObservableField
import com.hhi.myapplication.base.BaseViewModel
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl

class RecentSearchViewModel : BaseViewModel() {
    private val naverRepositoryDataSource = NaverRepositoryDataSourceImpl()
    val queryList = ObservableField<List<String>>()

    fun searchRecentQuery() {
        loading.set(View.GONE)
        val list = naverRepositoryDataSource.getQueryList()
        queryList.set(list)
        loading.set(View.GONE)
    }
}