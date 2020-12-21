package com.example.studyfork.recent

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.repository.Repository

class RecentSearchViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    val recentSearchList: MutableLiveData<List<String>> = MutableLiveData()
    val showListIsEmpty: MutableLiveData<Unit> = MutableLiveData()

    fun getRecentSearchList() {
        val result = repository.getRecentSearchList().reversed()
        if (result.isEmpty()) {
            showListIsEmpty.value = Unit
        } else {
            recentSearchList.value = result
        }
    }
}