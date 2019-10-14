package com.architecture.study.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.architecture.study.base.BaseViewModel
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.remote.CoinRemoteDataSourceListener
import com.architecture.study.network.model.TickerResponse

class TickerViewModel(
    coinRepository: CoinRepository,
    private val monetaryUnitList: List<String>
) : BaseViewModel<CoinRepository>(coinRepository) {

    val tickerList = MutableLiveData<List<Ticker>>()

    private val onClick: (ticker: Ticker) -> Unit =
        { ticker ->
            Log.d("TickerViewModel", "$ticker")
        }


    fun getTickerList(marketNameList: List<String>) {
        repository.getTickerList(
            marketNameList.joinToString(),
            object :
                CoinRemoteDataSourceListener<TickerResponse> {
                override fun onSuccess(dataList: List<TickerResponse>) {
                    val convertTickerList = mutableListOf<Ticker>()
                    dataList.map {
                        convertTickerList.add(it.toTicker(monetaryUnitList, onClick))
                    }
                    tickerList.value = convertTickerList
                }

                override fun onEmpty(str: String) {
                    exceptionMessage.value = str
                }

                override fun onFailure(str: String) {
                    exceptionMessage.value = str
                }
            })
    }
}