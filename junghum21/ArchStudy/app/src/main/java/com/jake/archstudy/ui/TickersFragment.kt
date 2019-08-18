package com.jake.archstudy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.FragmentTickersBinding
import com.jake.archstudy.network.response.TickerResponse
import com.jake.archstudy.ui.adapter.TickersAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TickersFragment : Fragment() {

    companion object {

        const val ARGS_MARKET_NAME = "ARGS_MARKET_NAME"

        fun newInstance(marketName: String): TickersFragment {
            return TickersFragment().apply {
                arguments = bundleOf(ARGS_MARKET_NAME to marketName)
            }
        }

    }

    private lateinit var binding: FragmentTickersBinding

    private val marketName by lazy { arguments?.getString(ARGS_MARKET_NAME) }

    private val repository = UpbitRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTickersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
                        response.body()?.let { setTickers(it) }
                    }
                }
            })
    }

    private fun setTickers(tickers: List<TickerResponse>) {
        binding.rvTickers.run {
            val adapter = adapter as? TickersAdapter ?: TickersAdapter().apply { adapter = this }
            adapter.set(tickers)
            addItemDecoration(DividerItemDecoration(requireContext(), RecyclerView.VERTICAL))
        }
    }

}