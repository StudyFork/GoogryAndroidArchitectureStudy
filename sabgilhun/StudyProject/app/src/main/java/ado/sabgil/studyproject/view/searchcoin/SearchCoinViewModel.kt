package ado.sabgil.studyproject.view.searchcoin

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.ext.isEmpty
import ado.sabgil.studyproject.ext.isNotEmpty
import ado.sabgil.studyproject.view.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo

class SearchCoinViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    private val _coinList = MutableLiveData<List<Ticker>>()
    val coinList: LiveData<List<Ticker>>
        get() = _coinList

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
        if (disposables.isNotEmpty()) {
            disposables.clear()
        }

        coinRepository.subscribeCoinDataByCoinName(
            coinName.toUpperCase(),
            { response ->
                endLoading()
                if (response.isNotEmpty()) {
                    currentCoin = coinName
                    _coinList.value = response
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