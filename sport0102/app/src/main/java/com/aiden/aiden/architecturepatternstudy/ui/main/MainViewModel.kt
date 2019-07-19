package com.aiden.aiden.architecturepatternstudy.ui.main

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.util.StringUtil

class MainViewModel(private val context: Context, private val upbitRepository: UpbitRepository) {

    private lateinit var marketName: String

    private val _tickerList = MutableLiveData<List<TickerResponse>>()
    val tickerList: LiveData<List<TickerResponse>> get() = _tickerList

    private val _isDataLoadingError = MutableLiveData<Boolean>()
    val isDataLoadingError: LiveData<Boolean> get() = _isDataLoadingError

    val searchKeyword = MutableLiveData<String>()

    fun loadMarketList(targetMarket: String) {

        marketName = targetMarket

        upbitRepository.getMarketList(onSuccess = {
            val modifiedMarketList =
                it.filter { market -> market.startsWith(targetMarket, true) }
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
                (context as Activity).runOnUiThread {
                    _tickerList.value =
                        it.map { tickerResponse -> StringUtil.modifyTicker(tickerResponse) }
                }
            },

            onFail = {
                (context as Activity).runOnUiThread {
                    _isDataLoadingError.value = true
                }
            }

        )

    }

    fun searchTickerByKeyword(keywords: List<String>) {

        upbitRepository.getTickerList(keywords,
            true,
            onSuccess = {
                (context as Activity).runOnUiThread {
                    _tickerList.value =
                        it.map { tickerResponse -> StringUtil.modifyTicker(tickerResponse) }
                }
            },

            onFail = {
                (context as Activity).runOnUiThread {
                    _isDataLoadingError.value = true
                }
            })

    }

    fun clearKeyword() {

        searchKeyword.value = ""

    }

}
