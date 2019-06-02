package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
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
        coinRepository.subscribeCoinDataByCurrency(baseCurrency,
            { response ->
                hideProgressBar()
                coinList = response
            }, { error ->
                hideProgressBar()
                showToast(error)
            }
        ).addTo(disposables)
    }

    fun unSubscribeRemote() {
        coinRepository.unSubscribeCoinData()
        disposables.clear()
    }
}