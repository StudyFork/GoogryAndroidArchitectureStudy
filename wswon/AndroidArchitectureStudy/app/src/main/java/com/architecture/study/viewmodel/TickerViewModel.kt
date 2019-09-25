package com.architecture.study.viewmodel

import androidx.databinding.ObservableField
import com.architecture.study.base.BaseViewModel
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.model.TickerResponse

class TickerViewModel(
    coinRepository: CoinRepository,
    private val monetaryUnitList: List<String>
) : BaseViewModel<CoinRepository>(coinRepository) {

    val tickerList = ObservableField<List<Ticker>>()

    fun getTickerList(marketNameList: List<String>) {
        repository.getTickerList(
            marketNameList.joinToString(),
            object : CoinRemoteDataSourceListener<TickerResponse> {
                override fun onSuccess(dataList: List<TickerResponse>) {
                    val convertTickerList = mutableListOf<Ticker>()
                    dataList.map {
                        convertTickerList.add(it.toTicker(monetaryUnitList))
                    }
                    tickerList.set(convertTickerList)
                }

                override fun onEmpty(str: String) {
                    exceptionMessage.set(str)
                }

                override fun onFailure(str: String) {
                    exceptionMessage.set(str)
                }
            })
    }
}