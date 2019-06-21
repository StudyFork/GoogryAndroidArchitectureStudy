package ado.sabgil.studyproject.view.coinlist

import ado.sabgil.studyproject.data.CoinRepository
import ado.sabgil.studyproject.data.enums.SortingCriteria
import ado.sabgil.studyproject.data.model.Ticker
import ado.sabgil.studyproject.view.base.BaseViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import io.reactivex.rxkotlin.addTo

class CoinListViewModel(
    private val baseCurrency: String,
    private val coinRepository: CoinRepository
) : BaseViewModel() {

    private val _coinList = MutableLiveData<List<Ticker>>()

    private val _sortingCriteria = MutableLiveData<SortingCriteria>()

    private val _sortingOrder = MutableLiveData<Boolean>(false)

    private val _sortedCoinList = MediatorLiveData<List<Ticker>>()
    val sortedCoinList: LiveData<List<Ticker>>
        get() = _sortedCoinList

    init {
        _sortedCoinList.addSource(_sortingCriteria) {
            if (_sortedCoinList.value != null && _sortingOrder.value != null) {
                _sortedCoinList.value = coinSort(_sortedCoinList.value!!, it, _sortingOrder.value!!)
            }
        }

        _sortedCoinList.addSource(_sortingOrder) {
            if (_sortedCoinList.value != null && _sortingCriteria.value != null) {
                _sortedCoinList.value = coinSort(_sortedCoinList.value!!, _sortingCriteria.value!!, it)
            }
        }

        _sortedCoinList.addSource(_coinList) {
            if (_sortingCriteria.value != null && _sortingOrder.value != null) {
                _sortedCoinList.value = coinSort(it, _sortingCriteria.value!!, _sortingOrder.value!!)
            } else {
                _sortedCoinList.value = it
            }
        }
    }

    fun setSortingCriteria(sortingCriteria: SortingCriteria) {
        _sortingCriteria.value = sortingCriteria
    }

    fun setSortingOrder(sortingOrder: Boolean) {
        _sortingOrder.value = sortingOrder
    }

    fun subscribeRemote() {
        startLoading()
        coinRepository.subscribeCoinDataByCurrency(
            baseCurrency,
            { response ->
                endLoading()
                _coinList.value = response
            }, { error ->
                endLoading()
                handleErrorMessage(error)
            }
        ).addTo(disposables)
    }

    fun unSubscribeRemote() {
        coinRepository.unSubscribeCoinData()
        disposables.clear()
    }

    private fun coinSort(
        tickers: List<Ticker>,
        sortingCriteria: SortingCriteria,
        ascendingOrder: Boolean
    ): List<Ticker> {

        return when (sortingCriteria) {
            SortingCriteria.COIN_NAME ->
                if (ascendingOrder) {
                    tickers.sortedBy(Ticker::coinName)
                } else {
                    tickers.sortedByDescending(Ticker::coinName)
                }
            SortingCriteria.CURRENT_VALUE ->
                if (ascendingOrder) {
                    tickers.sortedBy(Ticker::currentValue)
                } else {
                    tickers.sortedByDescending(Ticker::currentValue)
                }
            SortingCriteria.NET_CHANGE ->
                if (ascendingOrder) {
                    tickers.sortedBy(Ticker::changeRate)
                } else {
                    tickers.sortedByDescending(Ticker::changeRate)
                }

            SortingCriteria.ACC_TRADE_PRICE ->
                if (ascendingOrder) {
                    tickers.sortedBy(Ticker::accTradePrice)
                } else {
                    tickers.sortedByDescending(Ticker::accTradePrice)
                }
        }
    }

}