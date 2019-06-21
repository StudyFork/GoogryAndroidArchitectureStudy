package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class SearchCoinViewModelFactory(
    private val coinRepository: CoinRepository
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SearchCoinViewModel(coinRepository) as T
    }
}