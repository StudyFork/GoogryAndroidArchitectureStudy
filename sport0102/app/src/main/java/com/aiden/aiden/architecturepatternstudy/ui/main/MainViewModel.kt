package com.aiden.aiden.architecturepatternstudy.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.util.StringUtil

class MainViewModel(private val upbitRepository: UpbitRepository) : ViewModel() {

    private val _tickerList = MutableLiveData<List<TickerResponse>>()
    val tickerList: LiveData<List<TickerResponse>> get() = _tickerList

    private val _isDataLoadingError = MutableLiveData<Boolean>()
    val isDataLoadingError: LiveData<Boolean> get() = _isDataLoadingError

    fun loadMarketList(marketName: String) {

        upbitRepository.getMarketList(onSuccess = {
            val modifiedMarketList =
                it.filter { market -> market.startsWith(marketName, true) }
            loadAllTickerList(modifiedMarketList)
        },

            onFail = {
                _isDataLoadingError.value = true
            }

        )

    }

    private fun loadAllTickerList(marketList: List<String>) {

        upbitRepository.getTickerList(marketList,
            false,
            onSuccess = {
                _tickerList.postValue(it.map { tickerResponse ->
                    StringUtil.modifyTicker(
                        tickerResponse
                    )
                })
            },

            onFail = {
                _isDataLoadingError.postValue(true)
            }

        )

    }

    fun searchTickerByKeyword(keywords: List<String>) {

        upbitRepository.getTickerList(keywords,
            true,
            onSuccess = {
                _tickerList.postValue(it.map { tickerResponse ->
                    StringUtil.modifyTicker(
                        tickerResponse
                    )
                })
            },

            onFail = {
                _isDataLoadingError.postValue(true)
            }
        )

    }

}
