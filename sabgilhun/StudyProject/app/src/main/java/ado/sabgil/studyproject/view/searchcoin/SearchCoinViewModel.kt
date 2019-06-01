package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.ext.isEmpty
import ado.sabgil.studyproject.view.base.BaseViewModel
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
            showToast("검색할 코인을 입력해주세요.")
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
        showProgressBar()
        if (disposables.size() > 0) {
            disposables.clear()
        }

        coinRepository.getCoinDataChangeWithCoinName(coinName.toUpperCase(),
            { response ->
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
            }
        ).apply { disposables.add(this) }

    }
}