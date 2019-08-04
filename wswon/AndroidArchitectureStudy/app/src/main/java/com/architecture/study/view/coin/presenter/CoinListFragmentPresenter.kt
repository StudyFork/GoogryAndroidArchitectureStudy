package com.architecture.study.view.coin.presenter

import com.architecture.study.data.repository.CoinRepository
import com.architecture.study.data.source.CoinRemoteDataSourceListener
import com.architecture.study.network.RetrofitInstanceCallBack
import com.architecture.study.network.model.TickerResponse

class CoinListFragmentPresenter(
    private val coinRepository: CoinRepository,
    private val coinListView: CoinListFragmentContract.View,
    override var isConnectApi: Boolean
) : CoinListFragmentContract.Presenter, RetrofitInstanceCallBack {


    override fun start() {
        if(!isConnectApi){
            coinRepository.setRetrofitInstance(this)
        }
    }

    override fun getTickerList(marketNames: String) {
        if(isConnectApi){
            coinRepository.getTickerList(marketNames, object :
                CoinRemoteDataSourceListener<TickerResponse> {
                override fun onSucess(dataList: List<TickerResponse>) {
                    coinListView.showTickerList(dataList)
                }

                override fun onEmpty(str: String) {
                    coinListView.showEmptyTickerData(str)
                }

                override fun onFailure(str: String) {
                    coinListView.showFailureGetTickerData(str)
                }
            })
        }
    }

    override fun onLoaded() {
        isConnectApi = true
        if(coinListView.isActive){
            coinListView.successConnectApi()
        }
    }

    override fun onNotLoaded() {
        isConnectApi = false
        if(coinListView.isActive) {
            coinListView.showFailedConnectError()
        }
    }
}
