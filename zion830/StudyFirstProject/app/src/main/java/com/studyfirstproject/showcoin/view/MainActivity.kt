package com.studyfirstproject.showcoin.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.studyfirstproject.R
import com.studyfirstproject.adapter.CoinRecyclerViewAdapter
import com.studyfirstproject.data.CoinRepository
import com.studyfirstproject.data.model.TickerModel
import com.studyfirstproject.showcoin.CoinContract
import com.studyfirstproject.showcoin.CoinPresenter
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast


class MainActivity : AppCompatActivity(), CoinContract.View, SwipeRefreshLayout.OnRefreshListener {
    override lateinit var presenter: CoinContract.Presenter
    private lateinit var coinAdapter: CoinRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = CoinPresenter(this, CoinRepository())
        initView()
    }

    override fun initView() {
        coinAdapter = CoinRecyclerViewAdapter()
        rv_main.adapter = coinAdapter
        layout_main_swipe.setOnRefreshListener(this)

        getMarketData()
    }

    private fun getMarketData() {
        presenter.getMarketData()
    }

    override fun setCoinInfo(coinData: List<TickerModel>) {
        coinAdapter.setCoinList(coinData)
    }

    override fun showProgress() {
        progress_main.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_main.visibility = View.INVISIBLE
    }

    override fun hideRefreshIcon() {
        if (layout_main_swipe.isRefreshing)
            layout_main_swipe.isRefreshing = false
    }

    override fun notifyError(msg: String, reason: String?) {
        toast(msg)
        Log.e(localClassName, reason ?: "No error message")
    }

    override fun onRefresh() {
        getMarketData()
    }
}
