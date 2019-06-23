package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.View
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment(val market: String) : BaseFragment(R.layout.fragment_main), MainContract.View {


    override lateinit var presenter: MainContract.Presenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = start()

    override fun start() {
        presenter.loadMarketList(market)
    }

    override fun showTickerList(tickerList: List<TickerModel>) {
        fragment_ticker_list_rv.adapter = TickerListAdapter().apply {
            setData(tickerList)
            notifyDataSetChanged()
        }
    }

    override fun showErrorToast() {
        toastM(getString(R.string.all_error_load_data_fail))
    }

}