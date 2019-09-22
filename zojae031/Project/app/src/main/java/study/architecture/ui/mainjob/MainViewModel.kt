package study.architecture.ui.mainjob

import androidx.databinding.ObservableField
import study.architecture.ui.coinjob.CoinFragment

class MainViewModel(
    private val adapter: MainPageAdapter
) {
    val mainAdapter = ObservableField<MainPageAdapter>()
    val pageLimit: Int = CoinFragment.FragIndex.values().size

    fun initView() {
        mainAdapter.set(adapter)
    }
}