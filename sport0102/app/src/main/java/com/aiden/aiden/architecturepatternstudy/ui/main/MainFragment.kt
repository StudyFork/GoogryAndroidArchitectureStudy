package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.View
import com.aiden.aiden.architecturepatternstudy.api.Retrofit.retrofit
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.data.model.TickerModel
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : BaseFragment(com.aiden.aiden.architecturepatternstudy.R.layout.fragment_main), MainContract.View {

    override lateinit var presenter: MainContract.Presenter

    private val upbitApi by lazy { retrofit.create(UpbitApi::class.java) }

    private lateinit var marketName: String
    private val error = "error"

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
        presenter = MainPresenter(UpbitRepository.getInstance(UpbitRemoteDataSource(upbitApi)), this)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        presenter.loadMarketList(marketName)
        super.onResume()
    }

    override fun showTickerList(tickerList: List<TickerModel>) {
        fragment_ticker_list_rv.adapter = TickerListAdapter().apply {
            setData(tickerList)
            notifyDataSetChanged()
        }
    }

    override fun showErrorToast() {
        toastM(getString(com.aiden.aiden.architecturepatternstudy.R.string.all_error_load_data_fail))
    }

}