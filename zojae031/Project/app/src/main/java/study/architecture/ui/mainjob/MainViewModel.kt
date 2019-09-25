package study.architecture.ui.mainjob

import study.architecture.ui.coinjob.CoinFragment

class MainViewModel {
    val pageLimit: Int = CoinFragment.FragIndex.values().size
}