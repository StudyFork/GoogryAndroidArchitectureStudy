package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.View
import com.aiden.aiden.architecturepatternstudy.api.Retrofit.retrofit
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import kotlinx.android.synthetic.main.fragment_main.*
import android.widget.Toast
import android.R.attr.password
import androidx.databinding.Observable.OnPropertyChangedCallback
import android.R
import android.app.Activity
import androidx.databinding.DataBindingUtil




class MainFragment : BaseFragment(com.aiden.aiden.architecturepatternstudy.R.layout.fragment_main), MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    private val upbitApi by lazy { retrofit.create(UpbitApi::class.java) }

    private lateinit var marketName: String

    private val error = "error"

    private lateinit var mBinding : DataBindingUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            it.getString("marketName")?.let { marketName ->
                if (marketName == error) {
                    showErrorToast()
                    return
                }
                this.marketName = marketName
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        presenter.loadMarketList(marketName)
        super.onResume()
    }

    override fun showTickerList(tickerList: List<TickerResponse>) {
        fragment_ticker_list_rv.adapter = TickerListAdapter().apply {
            setData(tickerList)
            notifyDataSetChanged()
        }
    }

    override fun showErrorToast() {
        toastM(getString(com.aiden.aiden.architecturepatternstudy.R.string.all_error_load_data_fail))
    }

}