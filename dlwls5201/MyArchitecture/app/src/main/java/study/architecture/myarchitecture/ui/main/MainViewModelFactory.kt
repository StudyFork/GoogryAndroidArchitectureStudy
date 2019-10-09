package study.architecture.myarchitecture.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import study.architecture.myarchitecture.data.repository.UpbitRepository

class MainViewModelFactory(private val upbitRepository: UpbitRepository) :
    ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>) =
        MainViewModel(upbitRepository) as T

}