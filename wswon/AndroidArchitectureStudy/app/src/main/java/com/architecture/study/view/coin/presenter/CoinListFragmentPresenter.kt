package com.architecture.study.view.coin.presenter

import com.architecture.study.R
import com.architecture.study.data.model.Ticker
import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.model.TickerResponse

class CoinListFragmentPresenter(
    private val coinRepository: CoinRepository,
    private val coinListView: CoinListFragmentContract.View,
    private val monetaryUnitList: List<String>,
    override var isConnectApi: Boolean
) : CoinListFragmentContract.Presenter, RetrofitInstanceCallBack {


    override fun start() {
        if (!isConnectApi) {
            coinRepository.setRetrofitInstance(this)
        }
    }

    override fun getTickerList(marketNameList: List<String>) {
        if (isConnectApi) {
            coinRepository.getTickerList(marketNameList.joinToString(","), object :
                CoinRemoteDataSourceListener<TickerResponse> {
                override fun onSucess(dataList: List<TickerResponse>) {
                    val convertTickerList = mutableListOf<Ticker>()
                    dataList.map {
                        convertTickerList.add(it.toTicker(monetaryUnitList))
                    }
                    coinListView.showTickerList(convertTickerList)
                }

                override fun onEmpty(str: String) {
                    coinListView.showMessage(str)
                }

                override fun onFailure(str: String) {
                    coinListView.showMessage(str)
                }
            })
        }
    }

    override fun onLoaded() {
        isConnectApi = true
        if (coinListView.isActive) {
            coinListView.successConnectApi()
        }
    }

    override fun onNotLoaded() {
        isConnectApi = false
        if (coinListView.isActive) {
            coinListView.showMessage("Failed Connect Api Server")
        }
    }
}
