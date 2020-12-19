package com.wybh.androidarchitecturestudy.history

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wybh.androidarchitecturestudy.base.BaseViewModel
import com.wybh.androidarchitecturestudy.model.repository.Repository

class RecentSearchWordViewModel @ViewModelInject constructor(
    private val repository: Repository
) : BaseViewModel() {

    private val _wordList = MutableLiveData<List<String>>()
    val wordList: LiveData<List<String>> = _wordList

    init {
        getSearchWord()
    }

    private fun getSearchWord() {
        val wordList = repository.getSearchWord()
        if (wordList.isNullOrEmpty()) {
            return
        }
        _wordList.value = wordList.split(",")
    }
}