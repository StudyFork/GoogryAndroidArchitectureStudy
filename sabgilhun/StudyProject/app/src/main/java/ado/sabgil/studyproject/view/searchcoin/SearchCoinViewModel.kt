package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.ext.isEmpty
import ado.sabgil.studyproject.view.base.BaseViewModel
import io.reactivex.rxkotlin.addTo
import kotlin.properties.Delegates

class SearchCoinViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    var coinListListeners: ((List<Ticker>) -> Unit)? = null
    private var coinList: List<Ticker> by Delegates.observable(emptyList()) { _, _, newValue ->
        coinListListeners?.invoke(newValue)
    }

    var keyword = ""

    private var currentCoin = ""

    fun searchCoinByKeyword() {

        if (keyword.isBlank()) {
            handleErrorMessage("검색할 코인을 입력해주세요.")
            return
        }

        searchCoinData(keyword)
    }

    fun subscribeCoinData() {
        if (disposables.isEmpty() && currentCoin.isNotBlank()) {
            searchCoinData(currentCoin)
        }
    }

    fun unSubscribeCoinData() {
        disposables.clear()
    }

    private fun searchCoinData(coinName: String) {
        startLoading()
        if (disposables.size() > 0) {
            disposables.clear()
        }

        coinRepository.subscribeCoinDataByCoinName(
            coinName.toUpperCase(),
            { response ->
                endLoading()
                if (response.isNotEmpty()) {
                    currentCoin = coinName
                    coinList = response
                } else {
                    disposables.clear()
                    handleErrorMessage("검색한 코인에 대한 결과가 없습니다.")
                }
            }, { error ->
                endLoading()
                handleErrorMessage(error)
            }
        ).addTo(disposables)

    }
}