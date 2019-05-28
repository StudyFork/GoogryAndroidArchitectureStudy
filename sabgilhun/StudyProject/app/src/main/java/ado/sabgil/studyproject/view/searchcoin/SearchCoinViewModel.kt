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

    val showToastEventListeners = mutableListOf<((String) -> Unit)?>()

    val showProgressEventListeners = mutableListOf<(() -> Unit)?>()

    val hideProgressEventListeners = mutableListOf<(() -> Unit)?>()

    private var currentCoin = ""

    fun searchCoin(coinName: String) {

        if (coinName.isBlank()) {
            showToast("검색할 코인을 입력해주세요.")
            return
        }

        showProgressBar()
        if (disposables.size() > 0) {
            disposables.clear()
        }

        disposables.add(
            coinRepository
                .getCoinDataChangeWithCoinName(coinName.toUpperCase(), { response ->
                    hideProgressBar()
                    if (response.isNotEmpty()) {
                        currentCoin = coinName
                        coinList = response
                    } else {
                        disposables.clear()
                        showToast("검색한 코인에 대한 결과가 없습니다.")
                    }
                }, { error ->
                    hideProgressBar()
                    showToast(error)
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

    private fun showToast(message: String) {
        showToastEventListeners.map { it?.invoke(message) }
    }

    private fun showProgressBar() {
        showProgressEventListeners.map { it?.invoke() }
    }

    private fun hideProgressBar() {
        hideProgressEventListeners.map { it?.invoke() }
    }
}