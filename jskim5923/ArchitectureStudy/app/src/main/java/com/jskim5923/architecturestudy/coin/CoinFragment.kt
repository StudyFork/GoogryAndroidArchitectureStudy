package com.jskim5923.architecturestudy.coin

import android.os.Bundle
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.base.BaseFragment
import com.jskim5923.architecturestudy.databinding.LayoutCoinFragmentBinding

class CoinFragment : BaseFragment<LayoutCoinFragmentBinding>(
    R.layout.layout_coin_fragment
) {
    override val viewModel = CoinViewModel()

    private val coinListAdapter = CoinListAdapter()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        viewModel.getTickerList(arguments?.getString(KEY_MARKET))
    }

    private fun initView() {
        binding.run {
            recyclerView.adapter = coinListAdapter
            coinViewModel = viewModel
        }
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