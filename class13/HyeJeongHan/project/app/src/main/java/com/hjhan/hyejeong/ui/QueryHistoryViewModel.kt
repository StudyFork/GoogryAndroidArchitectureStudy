package com.hjhan.hyejeong.ui

import com.hjhan.hyejeong.data.repository.NaverRepository

class QueryHistoryViewModel(private val repositoryImpl: NaverRepository) {

    fun getRecentQueryList() = repositoryImpl.getQueryList()

}