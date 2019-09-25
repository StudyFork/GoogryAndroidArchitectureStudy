package com.architecture.study.viewmodel

import androidx.databinding.ObservableField
import com.architecture.study.base.BaseViewModel
import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.model.MarketResponse


class MarketViewModel(private val coinRepository: CoinRepository) : BaseViewModel() {

    val exceptionMessage = ObservableField<String>()

    val marketList = ObservableField<List<MarketResponse>>()

    fun getMarketList() {
        coinRepository.getMarketList(
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