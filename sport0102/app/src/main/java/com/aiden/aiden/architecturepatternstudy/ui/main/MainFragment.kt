package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.model.TickerModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment(val market: String) : Fragment(), MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        presenter.loadMarketList(market)
        return view
    }


    override fun showTickerList(tickerList: ArrayList<TickerModel>) {
        fragment_ticker_list_rv.layoutManager = LinearLayoutManager(context)
        fragment_ticker_list_rv.adapter = TickerListAdapter().apply {
            setData(tickerList)
            notifyDataSetChanged()
        }
    }

    override fun showErrorToast() {
        Toast.makeText(context, getString(R.string.all_error_load_data_fail), Toast.LENGTH_SHORT).show()
    }

}