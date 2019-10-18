package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.seonoh.seonohapp.databinding.CoinFragmentBinding
import com.example.seonoh.seonohapp.model.CoinViewModel

class CoinFragment : BaseFragment<CoinFragmentBinding>(
    R.layout.coin_fragment
) {

    override val viewModel = CoinViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            coinViewModel = viewModel
            krwRecyclerView.adapter = CoinAdapter()
        }
        getMarketInfo()
    }

    private fun getMarketInfo(){
        arguments?.getString(MARKET)?.let {
            viewModel.loadData(it)
        } ?: Toast.makeText(
            activity,
            resources.getString(R.string.empty_market_text),
            Toast.LENGTH_LONG
        ).show()
    }

    companion object {
        private const val MARKET = "market"

        fun newInstance(data: String): CoinFragment {
            val fragment = CoinFragment()
            val bundle = Bundle()
            bundle.putString(MARKET, data)
            fragment.arguments = bundle
            return fragment
        }
    }

}
