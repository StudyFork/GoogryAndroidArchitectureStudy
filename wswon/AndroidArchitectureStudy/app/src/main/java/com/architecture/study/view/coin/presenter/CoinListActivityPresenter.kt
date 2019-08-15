package com.architecture.study.view.coin.presenter

import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.model.MarketResponse

class CoinListActivityPresenter(
    private val coinRepository: CoinRepository,
    private val coinListView: CoinListActivityContract.View,
    override var isConnectApi: Boolean
) : CoinListActivityContract.Presenter, RetrofitInstanceCallBack {

    override fun start() {
        if(!isConnectApi){
            coinRepository.setRetrofitInstance(this)
        }
    }

    override fun getMarketList() {
        if(isConnectApi){
            coinRepository.getMarketList(object :
                CoinRemoteDataSourceListener<MarketResponse> {
                override fun onSucess(dataList: List<MarketResponse>) {
                    coinListView.setTabPager(dataList)
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
        getMarketList()
    }

    override fun onNotLoaded() {
        isConnectApi = false
        coinListView.showMessage("Failed Connect Api Server")
    }
}