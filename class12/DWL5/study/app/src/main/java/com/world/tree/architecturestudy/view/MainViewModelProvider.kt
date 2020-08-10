package com.world.tree.architecturestudy.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.world.tree.architecturestudy.model.repository.remote.NaverRepository
import java.lang.IllegalArgumentException

class MainViewModelProvider(private val repository: NaverRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository as NaverRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}