package com.example.seonoh.seonohapp.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.seonoh.seonohapp.model.MainViewModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: CoinRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}