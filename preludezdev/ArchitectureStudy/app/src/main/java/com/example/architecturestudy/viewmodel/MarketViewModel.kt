package com.example.architecturestudy.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecturestudy.data.Coin
import com.example.architecturestudy.data.source.CoinsRepositoryImpl
import com.example.architecturestudy.util.Filter

class MarketViewModel(
    private val repository: CoinsRepositoryImpl,
    private val keyMarket: String?
) : ViewModel() {

    private val _coinList = MutableLiveData<List<Coin>>()
    val coinList: LiveData<List<Coin>> get() = _coinList

    private val _isProgressed = MutableLiveData<Boolean>()
    val isProgressed: LiveData<Boolean> get() = _isProgressed

    private val _notificationMsg = MutableLiveData<String>()
    val notificationMsg: LiveData<String> get() = _notificationMsg

    private var mCoinList = mutableListOf<Coin>()

    init {
        loadData(Filter.Type.ACC_TRADE_PRICE_H, Filter.Order.ASC)
    }

    fun loadData(type: Filter.Type, order: Filter.Order) {
        if (keyMarket != null) {
            showProgressBar()

            repository
                .getCoinTickers(keyMarket, { coinTickerResponses ->
                    if (!coinTickerResponses.isNullOrEmpty()) {
                        mCoinList.clear()
                        mCoinList.addAll(coinTickerResponses.map { it.convertTickerIntoCoin() })

                        sortData(type, order)
                    }

                    hideProgressBar()
                }, {
                    hideProgressBar()
                    showToastErrorMessage(it)
                })
        } else {
            showToastErrorMessage("데이터를 불러올 수 없습니다 (데이터 요청 키가 없습니다.)")
        }
    }

    fun sortData(type: Filter.Type, order: Filter.Order) {
        if (order == Filter.Order.ASC) {
            when (type) {
                Filter.Type.MARKET -> mCoinList.sortByDescending { it.market }
                Filter.Type.TRADE_PRICE -> mCoinList.sortByDescending { it.tradePriceVal }
                Filter.Type.SIGNED_CHANGED_RATE -> mCoinList.sortByDescending { it.signedChangeRateVal }
                Filter.Type.ACC_TRADE_PRICE_H -> mCoinList.sortByDescending { it.accTradePriceHVal }
            }
        } else {
            when (type) {
                Filter.Type.MARKET -> mCoinList.sortBy { it.market }
                Filter.Type.TRADE_PRICE -> mCoinList.sortBy { it.tradePriceVal }
                Filter.Type.SIGNED_CHANGED_RATE -> mCoinList.sortBy { it.signedChangeRateVal }
                Filter.Type.ACC_TRADE_PRICE_H -> mCoinList.sortBy { it.accTradePriceHVal }
            }
        }

        _coinList.value = mCoinList
    }

    private fun showToastErrorMessage(errorMsg: String) {
        _notificationMsg.value = errorMsg
    }

    private fun showProgressBar() {
        _isProgressed.value = true
    }

    private fun hideProgressBar() {
        _isProgressed.value = false
    }

}