package com.jake.archstudy.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseFragment
import com.jake.archstudy.data.model.Ticker
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.FragmentTickersBinding
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.network.response.TickerResponse
import com.jake.archstudy.ui.adapter.TickersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TickersFragment : BaseFragment<FragmentTickersBinding>(R.layout.fragment_tickers) {

    private val marketName by lazy { arguments?.getString(ARGS_MARKET_NAME) }

    private val repository = UpbitRepository(ApiUtil.getUpbitService())

    private val tickersAdapter = TickersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTickers()
        getTickers()
    }

    private fun getTickers() {
        repository.getTicker(marketName ?: "")
            .enqueue(object : Callback<List<TickerResponse>?> {
                override fun onFailure(call: Call<List<TickerResponse>?>, t: Throwable) {
                    t.printStackTrace()
                }

                override fun onResponse(
                    call: Call<List<TickerResponse>?>,
                    response: Response<List<TickerResponse>?>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { body ->
                            val tickers = body.map { it.toTicker() }
                            setTickers(tickers)
                        }
                    }
                }
            })
    }

    private fun initTickers() {
        binding.rvTickers.run {
            adapter = tickersAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

    private fun setTickers(tickers: List<Ticker>) {
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