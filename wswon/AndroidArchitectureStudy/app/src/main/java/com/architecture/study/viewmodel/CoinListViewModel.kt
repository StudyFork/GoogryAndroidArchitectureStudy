package com.architecture.study.viewmodel

import androidx.databinding.ObservableField
import com.architecture.study.base.BaseViewModel
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.model.MarketResponse
import com.architecture.study.network.model.TickerResponse


class CoinListViewModel(
    private val coinRepository: CoinRepository,
    private val monetaryUnitList: List<String>
) : BaseViewModel() {


    var isConnectedApi: Boolean = false

    init {
        coinRepository.setRetrofitInstance(object : RetrofitInstanceCallBack{
            override fun onLoaded() {
                isConnectedApi = true
            }

            override fun onNotLoaded() {
                isConnectedApi = false
            }
        })
    }



    var marketList = ObservableField<List<MarketResponse>>()

    var tickerList = ObservableField<List<Ticker>>()

    fun getMarketList(callback: (message: String) -> Unit) {
        coinRepository.getMarketList(
            object : CoinRemoteDataSourceListener<MarketResponse> {
                override fun onSucess(dataList: List<MarketResponse>) {
                    marketList.set(dataList)
                    callback(SUCCESS)
                }

                override fun onEmpty(str: String) {
                    callback(str)
                }

                override fun onFailure(str: String) {
                    callback(str)
                }
            })
    }

    fun getTickerList(marketNameList: List<String>, callback: (notSucceedMessage: String) -> Unit) {
        coinRepository.getTickerList(
            marketNameList.joinToString(","),
            object : CoinRemoteDataSourceListener<TickerResponse> {
                override fun onSucess(dataList: List<TickerResponse>) {
                    val convertTickerList = mutableListOf<Ticker>()
                    dataList.map {
                        convertTickerList.add(it.toTicker(monetaryUnitList))
                    }
                    tickerList.set(convertTickerList)
                }

                override fun onEmpty(str: String) {
                    callback(str)
                }

                override fun onFailure(str: String) {
                    callback(str)
                }
            })
    }

    companion object {
        const val SUCCESS = "SUCCESS"
    }

}