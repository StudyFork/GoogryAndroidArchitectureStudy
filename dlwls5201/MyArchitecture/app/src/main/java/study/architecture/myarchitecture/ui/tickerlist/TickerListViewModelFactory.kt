package study.architecture.myarchitecture.ui.tickerlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import study.architecture.myarchitecture.data.repository.UpbitRepository

class TickerListViewModelFactory(
    private val upbitRepository: UpbitRepository,
    private val keyMarket: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TickerListViewModel(upbitRepository, keyMarket) as T
    }

}