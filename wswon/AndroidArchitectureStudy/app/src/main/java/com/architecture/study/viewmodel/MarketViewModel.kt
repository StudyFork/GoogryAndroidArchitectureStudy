package com.architecture.study.viewmodel

import androidx.databinding.ObservableField
import com.architecture.study.base.BaseViewModel
import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.model.MarketResponse


class MarketViewModel(coinRepository: CoinRepository) : BaseViewModel<CoinRepository>(coinRepository) {

    val marketList = ObservableField<List<MarketResponse>>()

    fun getMarketList() {
        repository.getMarketList(
            object : CoinRemoteDataSourceListener<MarketResponse> {
                override fun onSuccess(dataList: List<MarketResponse>) {
                    marketList.set(dataList)
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