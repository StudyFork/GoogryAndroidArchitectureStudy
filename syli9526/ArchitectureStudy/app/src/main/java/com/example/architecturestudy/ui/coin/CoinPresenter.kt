package com.example.architecturestudy.ui.coin

import com.example.architecturestudy.data.model.Ticker
import com.example.architecturestudy.data.repository.CoinRepositoryImpl
import com.example.architecturestudy.data.source.CoinDataSourceImpl
import com.example.architecturestudy.data.source.UpbitListener

class CoinPresenter(private val view: CoinContract.View) :
    CoinContract.Presenter {

    override fun getTickerList(marketName: String) {
        CoinRepositoryImpl(CoinDataSourceImpl.getInstance())
            .getTickerInfo(marketName, object : UpbitListener<Ticker> {

                override fun onResponse(dataList: List<Ticker>) {
                    view.setData(dataList)
                }

                override fun onFailure(str: String) {
                    view.showMessage(str)
                }

            })
    }
}