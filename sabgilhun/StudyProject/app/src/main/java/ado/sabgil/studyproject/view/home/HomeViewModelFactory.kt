package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.CoinRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class HomeViewModelFactory(
    private val coinRepository: CoinRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(coinRepository) as T
    }
}