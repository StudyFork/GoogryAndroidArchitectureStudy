package ado.sabgil.studyproject.view.home

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.view.base.BaseViewModel
import kotlin.properties.Delegates

class HomeViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    val marketListListeners = mutableListOf<((List<String>?) -> Unit)?>()

    private var marketList: List<String>? by Delegates.observable(emptyList()) { _, _, newValue ->
        marketListListeners.map {
            it?.invoke(newValue)
        }
    }

    fun loadMarketList() {
        // show progress bar

        disposables.add(coinRepository
            .loadMarketList({ response ->
                // hide progress bar
                marketList = response
            }, {
                // hide progress bar
                // show toast
            }
            ))
    }
}