package com.jake.archstudy.ui.tickers

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.jake.archstudy.R
import com.jake.archstudy.base.BaseFragment
import com.jake.archstudy.data.source.UpbitRemoteDataSource
import com.jake.archstudy.data.source.UpbitRepository
import com.jake.archstudy.databinding.FragmentTickersBinding
import com.jake.archstudy.network.ApiUtil
import com.jake.archstudy.util.ResourceProviderImpl

class TickersFragment :
    BaseFragment<FragmentTickersBinding, TickersViewModel>(R.layout.fragment_tickers) {

    override val viewModel by lazy {
        @Suppress("UNCHECKED_CAST")
        ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TickersViewModel(
                    UpbitRepository.getInstance(UpbitRemoteDataSource(ApiUtil.getUpbitService())),
                    marketName,
                    ResourceProviderImpl(requireContext())
                ) as T
            }
        }).get(TickersViewModel::class.java)
    }

    private val marketName by lazy { arguments?.getString(ARGS_MARKET_NAME) ?: "" }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.run {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
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