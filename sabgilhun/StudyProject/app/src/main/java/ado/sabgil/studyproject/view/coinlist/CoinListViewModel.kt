package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseViewModel
import kotlin.properties.Delegates

class CoinListViewModel(
    private val baseCurrency: String,
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    var coinListListeners: ((List<Ticker>) -> Unit)? = null

    private var coinList: List<Ticker> by Delegates.observable(emptyList()) { _, _, newValue ->
        coinListListeners?.invoke(newValue)
    }

    fun subscribeRemote() {
        showProgressBar()
        coinRepository.getCoinDataChangeWithCurrency(baseCurrency,
            { response ->
                hideProgressBar()
                coinList = response
            }, { error ->
                hideProgressBar()
                showToast(error)
            }
        ).apply { disposables.add(this) }
    }

    fun unSubscribeRemote() {
        coinRepository.unSubscribeCoinDataChange()
        disposables.clear()
    }
}