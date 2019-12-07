package com.jskim5923.architecturestudy.coin

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jskim5923.architecturestudy.R
import com.jskim5923.architecturestudy.adapter.CoinListAdapter
import com.jskim5923.architecturestudy.base.BaseFragment
import com.jskim5923.architecturestudy.databinding.LayoutCoinFragmentBinding
import javax.inject.Inject

class CoinFragment : BaseFragment<LayoutCoinFragmentBinding>(
    R.layout.layout_coin_fragment
) {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override val viewModel by lazy {
        ViewModelProviders.of(
            this,
            viewModelFactory
        )[CoinViewModel::class.java]
    }

    @Inject
    lateinit var coinListAdapter: CoinListAdapter

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