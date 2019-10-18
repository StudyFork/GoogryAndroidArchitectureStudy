package com.test.androidarchitecture.ui.ticker


import android.os.Bundle
import com.test.androidarchitecture.R
import com.test.androidarchitecture.adpter.TickerAdapter
import com.test.androidarchitecture.base.BaseFragment
import com.test.androidarchitecture.databinding.FragmentCoinBinding

class TickerFragment : BaseFragment<FragmentCoinBinding, TickerViewModel>(R.layout.fragment_coin){

    override val vm by lazy { TickerViewModel(arguments?.getString(MARKET_SEARCH) ?: "") }
    private val adapter by lazy { TickerAdapter() }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.coinRecyclerView.adapter = this.adapter
        binding.vm = vm
        vm.getTicker()
    }

    companion object {

        private const val MARKET_SEARCH: String = "marketSearch"

        fun getInstance(marketSearch: String): TickerFragment {
            val args = Bundle()
            args.putString(MARKET_SEARCH, marketSearch)
            val fragment = TickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
