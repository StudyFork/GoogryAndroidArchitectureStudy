package study.architecture.ui.mainjob

import androidx.databinding.ObservableField
import study.architecture.ui.coinjob.CoinFragment

class MainViewModel(
    adapter: MainPageAdapter
) {
    val mainAdapter = ObservableField<MainPageAdapter>()
    val pageLimit = ObservableField<Int>()

    init {
        mainAdapter.set(adapter)
    }

    fun initView() {
        pageLimit.set(CoinFragment.FragIndex.values().size)
    }
}