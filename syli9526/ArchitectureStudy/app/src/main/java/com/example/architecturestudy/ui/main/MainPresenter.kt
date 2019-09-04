package com.example.architecturestudy.ui.main


import com.example.architecturestudy.data.repository.CoinRepositoryImpl
import com.example.architecturestudy.data.source.UpbitListener
import com.example.architecturestudy.network.model.MarketResponse

class MainPresenter(private val view: MainContract.View):
    MainContract.Presenter {

    override fun getMarketList() {

        CoinRepositoryImpl.getInstance().getMarketInfo(object : UpbitListener<MarketResponse> {
            override fun onResponse(dataList: List<MarketResponse>) {
                view.setData(dataList)
            }

            override fun onFailure(str: String) {
                view.showMessage("Call Failure : $str")
            }

        })
    }
}