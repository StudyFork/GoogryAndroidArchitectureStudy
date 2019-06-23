package my.gong.studygong.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import my.gong.studygong.data.source.upbit.UpbitDataSource

class ViewModelFactoryImpl(
    private val upbitRepository: UpbitDataSource
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when (modelClass) {
            CoinViewModel::class.java -> {
                CoinViewModel(upbitRepository)
            }
            else -> throw IllegalArgumentException("")
        } as T
    }
}