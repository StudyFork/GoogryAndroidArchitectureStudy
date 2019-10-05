package com.jake.archstudy.ui.tickers

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseFragment
import com.jake.archstudy.data.model.Ticker
import com.jake.archstudy.data.source.UpbitRemoteDataSource
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.FragmentTickersBinding
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.ui.tickers.adapter.TickersAdapter

class TickersFragment :
    BaseFragment<FragmentTickersBinding>(R.layout.fragment_tickers),
    TickersContract.View {

    override val presenter by lazy {
        TickersPresenter(
            this,
            UpbitRepository.getInstance(UpbitRemoteDataSource(ApiUtil.getUpbitService())),
            marketName
        )
    }

    private val marketName by lazy { arguments?.getString(ARGS_MARKET_NAME) ?: "" }

    private val tickersAdapter = TickersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.start()
        initTickers()
    }

    private fun initTickers() {
        binding.rvTickers.run {
            adapter = tickersAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    override fun setTickers(tickers: List<Ticker>) {
        tickersAdapter.set(tickers)
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