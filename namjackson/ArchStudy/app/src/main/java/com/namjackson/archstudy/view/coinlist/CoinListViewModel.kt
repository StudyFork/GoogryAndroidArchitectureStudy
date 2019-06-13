package com.namjackson.archstudy.view.coinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.namjackson.archstudy.base.BaseViewModel
import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.TickerRepository
import com.namjackson.archstudy.util.Event

class CoinListViewModel(
    val tickerRepository: TickerRepository
) : BaseViewModel() {

    private var coinList = listOf<Ticker>()
        set(value) {
            field = value
            filteringCoinList()
        }

    private var _filterCoinList = MutableLiveData<List<Ticker>>()
    val filterCoinList: LiveData<List<Ticker>>
        get() = _filterCoinList

    private var _searchStr = MutableLiveData<String>("")
    val searchStr: LiveData<String>
        get() = _searchStr

    var baseCurrency = MutableLiveData<String>("")

    private lateinit var markets: String

    fun loadCoinList() {
        if (!this::markets.isInitialized) {
            initMarket()
        } else {
            getTickers(markets)
        }
    }

    fun initMarket() {
        _isLoading.postValue(true)
        tickerRepository.getMarketAll(
            baseCurrency = baseCurrency.value ?: "",
            success = {
                markets = it
                getTickers(markets)
            },
            fail = {
                _showToastEvent.value = Event(it)
                _isLoading.value = false
            }
        )
    }

    fun getTickers(markets: String) {
        tickerRepository.getTickers(
            markets = markets,
            success = {
                coinList = it
                _isLoading.value = false
            },
            fail = {
                _showToastEvent.value = Event(it)
                _isLoading.value = false
            }
        )
    }

    fun changeSearch(searchStr: CharSequence) {
        _searchStr.value = (searchStr.toString())
        filteringCoinList()
    }

    private fun filteringCoinList() {
        _filterCoinList.value = coinList.filter { it.name.contains(searchStr.value?.toUpperCase().toString()) }
    }
}