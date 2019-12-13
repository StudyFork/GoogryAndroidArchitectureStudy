package com.test.androidarchitecture.ui.ticker


import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.test.androidarchitecture.R
import com.test.androidarchitecture.base.BaseFragment
import com.test.androidarchitecture.databinding.FragmentCoinBinding

class TickerFragment : BaseFragment<FragmentCoinBinding, TickerViewModel>(R.layout.fragment_coin) {

    override val vm : TickerViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(TickerViewModel::class.java)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.vm = vm
        vm.start(arguments?.getString(MARKET_SEARCH) ?: "")
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
