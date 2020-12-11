package com.example.studyfork.recent

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.studyfork.base.BaseViewModel
import com.example.studyfork.data.repository.Repository
import io.reactivex.subjects.PublishSubject

class RecentSearchViewModel(private val repository: Repository) : BaseViewModel() {

    val recentSearchList: MutableLiveData<List<String>> = MutableLiveData()
    val showListIsEmpty: PublishSubject<Unit> = PublishSubject.create()

    fun getRecentSearchList() {
        val result = repository.getRecentSearchList().reversed()
        if (result.isEmpty()) {
            showListIsEmpty.onNext(Unit)
        } else {
            recentSearchList.value = result
        }
    }
}

class RecentSearchViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(RecentSearchViewModel::class.java)) {
            RecentSearchViewModel(repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}