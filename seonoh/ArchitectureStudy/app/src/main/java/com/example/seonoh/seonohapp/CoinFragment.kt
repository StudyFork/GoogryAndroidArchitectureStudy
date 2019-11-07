package com.example.seonoh.seonohapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.seonoh.seonohapp.databinding.CoinFragmentBinding
import com.example.seonoh.seonohapp.model.CoinViewModel
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl

class CoinFragment : BaseFragment<CoinFragmentBinding>(
    R.layout.coin_fragment
) {
    @Suppress("UNCHECKED_CAST")
    override val viewModel by lazy {
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(itemClass: Class<T>): T {
                return CoinViewModel(
                    CoinRepositoryImpl()
                ) as T
            }
        }).get(CoinViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.run {
            coinViewModel = viewModel
            krwRecyclerView.adapter = CoinAdapter()
        }
        getMarketInfo()
    }

    private fun getMarketInfo() {
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
