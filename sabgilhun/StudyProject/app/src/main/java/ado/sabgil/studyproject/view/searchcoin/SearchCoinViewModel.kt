package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseViewModel
import kotlin.properties.Delegates

class SearchCoinViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    val coinListListeners = mutableListOf<((List<Ticker>?) -> Unit)?>()

    private var coinList: List<Ticker>? by Delegates.observable(emptyList()) { _, _, newValue ->
        coinListListeners.map {
            it?.invoke(newValue)
        }
    }

    private var currentCoin = ""

    fun searchCoin(coinName: String) {

        if (disposables.size() > 0) {
            disposables.clear()
        }

        disposables.add(coinRepository
            .getCoinDataChangeWithCoinName(
                coinName.toUpperCase(), {
                    if (it.isNotEmpty()) {
                        currentCoin = coinName
                        coinList = it
                    } else {
                        disposables.clear()
                        // show toast
                    }
                }, {
                    // show toast
                })
        )
    }

    fun subscribeCoinData() {
        if (disposables.size() == 0 && currentCoin != "") {
            searchCoin(currentCoin)
        }
    }

    fun unSubscribeCoinData() {
        disposables.clear()
    }
}