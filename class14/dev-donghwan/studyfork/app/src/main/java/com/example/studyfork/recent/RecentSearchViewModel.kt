package com.example.studyfork.recent

import androidx.databinding.ObservableField
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.repository.Repository

class RecentSearchViewModel(private val repository: Repository) : BaseViewModel() {

    val recentSearchList: ObservableField<List<String>> = ObservableField()
    val showListIsEmpty: ObservableField<Unit> = ObservableField()

    fun getRecentSearchList() {
        val result = repository.getRecentSearchList()
        if (result.isEmpty()) {
            showListIsEmpty.set(Unit)
        } else {
            recentSearchList.set(result)
        }
    }
}