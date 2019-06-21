package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.CoinRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CoinListViewModelFactory(
    private val baseCurrency: String,
    private val coinRepository: CoinRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CoinListViewModel(baseCurrency, coinRepository) as T
    }
}