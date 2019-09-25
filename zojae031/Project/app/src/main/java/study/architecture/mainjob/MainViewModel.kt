package study.architecture.mainjob

import study.architecture.coinjob.CoinFragment

class MainViewModel {
    val pageLimit: Int = CoinFragment.FragIndex.values().size
}