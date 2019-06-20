package com.namjackson.archstudy.view.coinlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.namjackson.archstudy.base.BaseViewModel
import com.namjackson.archstudy.data.model.Ticker
import com.namjackson.archstudy.data.source.TickerRepository
import com.namjackson.archstudy.util.Event

class CoinListViewModel(
    val tickerRepository: TickerRepository
) : BaseViewModel() {

    private val coinList = MutableLiveData<List<Ticker>>()

    private val _filterCoinList = MediatorLiveData<List<Ticker>>()
    val filterCoinList: LiveData<List<Ticker>>
        get() = _filterCoinList

    private val _searchStr = MutableLiveData<String>("")
    val searchStr: LiveData<String>
        get() = _searchStr

    var baseCurrency = MutableLiveData<String>("")

    private lateinit var markets: String

    init {
        _filterCoinList.addSource(_searchStr, Observer { t ->
            _filterCoinList.value = filteringCoinList()
        })
        _filterCoinList.addSource(coinList, Observer { t ->
            _filterCoinList.value = filteringCoinList()
        })
        _filterCoinList.addSource(baseCurrency, Observer { t ->
            initMarket()
        })
    }


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
                coinList.value = it
                _isLoading.value = false
            },
            fail = {
                _showToastEvent.value = Event(it)
                _isLoading.value = false
            }
        )
    }

    fun changeSearch(searchStr: CharSequence) {
        _searchStr.value = searchStr.toString()
    }

    private fun filteringCoinList(): List<Ticker>? =
        coinList.value?.filter { it.name.contains(searchStr.value?.toUpperCase().toString()) }
}