package com.studyfirstproject.activity

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log.e
import android.view.View
import com.studyfirstproject.R
import com.studyfirstproject.adapter.CoinRecyclerViewAdapter
import com.studyfirstproject.model.TickerModel
import com.studyfirstproject.network.CoinApi
import com.studyfirstproject.network.RetrofitBuilder
import com.studyfirstproject.network.retrofitCallback
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {
    private lateinit var coinAdapter: CoinRecyclerViewAdapter
    private lateinit var service: CoinApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        service = RetrofitBuilder.getInstance()
        initView()
    }

    private fun initView() {
        coinAdapter = CoinRecyclerViewAdapter(arrayListOf())
        rv_main.adapter = coinAdapter
        layout_main_swipe.setOnRefreshListener(this)

        getMarketData()
    }

    private fun getMarketData() {
        showProgress()
        service.getAllMarket().enqueue(retrofitCallback { throwable, response ->
            response?.let {
                val marketList = response.body()!!
                    .map { it.market }
                    .filter { it.substring(0, it.indexOf("-")) == "KRW" }

                val marketStr = marketList.joinToString().replace(" ", "")
                getCoinData(marketStr)
            }
            throwable?.let {
                toast(getString(R.string.network_err))
                e(localClassName, throwable.message)
            }
        })
    }

    private fun getCoinData(marketStr: String) {
        service.getTickers(marketStr).enqueue(retrofitCallback { throwable, response ->
            response?.let {
                setCoinInfo(response.body() ?: listOf())
            }
            throwable?.let {
                toast(getString(R.string.network_err))
                e(localClassName, throwable.message)
            }
        })
    }

    private fun setCoinInfo(coinData: List<TickerModel>) {
        coinAdapter.coinList = ArrayList(coinData)
        coinAdapter.notifyDataSetChanged()
        hideProgress()
        hideRefreshIcon()
    }

    private fun showProgress() {
        progress_main.visibility = View.VISIBLE
    }

    private fun hideProgress() {
        progress_main.visibility = View.INVISIBLE
    }

    private fun hideRefreshIcon() {
        if (layout_main_swipe.isRefreshing)
            layout_main_swipe.isRefreshing = false
    }

    override fun onRefresh() {
        getMarketData()
    }
}
