package com.jake.archstudy.ui.tickers

import android.os.Bundle
import androidx.core.os.bundleOf
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseFragment
import com.jake.archstudy.data.source.UpbitRemoteDataSource
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.FragmentTickersBinding
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.ui.tickers.adapter.TickersAdapter

class TickersFragment :
    BaseFragment<FragmentTickersBinding>(R.layout.fragment_tickers) {

    private val tickersViewModel by lazy {
        TickersViewModel(
            UpbitRepository.getInstance(UpbitRemoteDataSource(ApiUtil.getUpbitService())),
            marketName
        )
    }

    private val marketName by lazy { arguments?.getString(ARGS_MARKET_NAME) ?: "" }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.run {
            vm = tickersViewModel
            lifecycle.addObserver(tickersViewModel)
            lifecycleOwner = this@TickersFragment
        }
        tickersViewModel.getTickers()
    }

    companion object {

        const val ARGS_MARKET_NAME = "ARGS_MARKET_NAME"

        fun newInstance(marketName: String): TickersFragment {
            return TickersFragment().apply {
                arguments = bundleOf(ARGS_MARKET_NAME to marketName)
            }
        }

    }

}