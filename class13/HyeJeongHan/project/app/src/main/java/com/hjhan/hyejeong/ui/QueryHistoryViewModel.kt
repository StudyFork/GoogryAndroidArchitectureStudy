package com.hjhan.hyejeong.ui

import androidx.databinding.ObservableField
import com.hjhan.hyejeong.data.repository.NaverRepository

class QueryHistoryViewModel(private val repositoryImpl: NaverRepository) {

    val queryList = ObservableField<List<String>>()

    fun getRecentQueryList() {
        queryList.set(repositoryImpl.getQueryList())
    }

}