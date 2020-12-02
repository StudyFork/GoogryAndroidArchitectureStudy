package com.example.androidarchitecturestudy.ui

import androidx.databinding.ObservableField
import com.example.androidarchitecturestudy.data.model.QueryHistory
import com.example.androidarchitecturestudy.data.repository.NaverRepository

class TitleHistoryViewModel(private val repository: NaverRepository) {
    val queryList = ObservableField<ArrayList<QueryHistory>>()

    fun getRecentTitleList() {
        queryList.set(repository.getMovieQueryList() as ArrayList<QueryHistory>?)
    }
}