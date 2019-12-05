package com.android.studyfork.ui.tickerlist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.studyfork.repository.UpbitRepositoryImpl
import com.android.studyfork.ui.main.viewmodel.MainViewModel
import javax.inject.Inject

class MainViewModelFactory @Inject constructor(private val repository: UpbitRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository = repository) as T
    }
}