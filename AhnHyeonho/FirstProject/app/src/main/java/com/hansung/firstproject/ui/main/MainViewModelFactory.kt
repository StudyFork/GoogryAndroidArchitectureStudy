package com.hansung.firstproject.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hansung.firstproject.data.repository.NaverRepository

class MainViewModelFactory(private val repository: NaverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}