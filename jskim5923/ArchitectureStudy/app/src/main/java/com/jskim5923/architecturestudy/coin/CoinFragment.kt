package com.jskim5923.architecturestudy.ui

import android.os.Bundle
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.base.BaseFragment
import com.jskim5923.architecturestudy.coin.CoinContract
import com.jskim5923.architecturestudy.coin.CoinPresenter
import com.jskim5923.architecturestudy.databinding.LayoutCoinFragmentBinding
import com.jskim5923.architecturestudy.model.Ticker

class CoinFragment : BaseFragment<LayoutCoinFragmentBinding>(
    R.layout.layout_coin_fragment
), CoinContract.View {
    override val presenter = CoinPresenter(this)

    private var coinListAdapter = CoinListAdapter()

    override fun initView() {
        binding.run {
            recyclerView.adapter = coinListAdapter
        }

        presenter.getTickerList(arguments?.getString(KEY_MARKET))
    }

    override fun updateRecyclerView(tickerList: List<Ticker>) {
        coinListAdapter.updateItem(tickerList)
    }

    companion object {
        const val KEY_MARKET = "KEY_MARKET"

        fun newInstance(market: String) = CoinFragment().apply {
            arguments = Bundle().apply {
                putString(KEY_MARKET, market)
            }
        }
    }

}