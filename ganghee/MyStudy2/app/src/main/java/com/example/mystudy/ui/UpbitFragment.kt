package com.example.mystudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.viewModels
import com.example.mystudy.BR
import com.example.mystudy.R
import com.example.mystudy.base.BaseFragment
import com.example.mystudy.base.BaseRecyclerViewAdapter
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.data.remote.UpbitRemoteDataSource
import com.example.mystudy.databinding.FragmentUpbitBinding
import com.example.mystudy.databinding.RvItemListBinding
import com.example.mystudy.viewmodel.UpbitViewModel

class UpbitFragment : BaseFragment<FragmentUpbitBinding>(R.layout.fragment_upbit) {

    private val upbitViewModel by viewModels<UpbitViewModel>()

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val firstMarket = arguments?.getString(MARKET_NAME)

        initViewModel()
        upbitViewModel.getTicker(firstMarket)
    }

    private fun initViewModel() {
        binding.run {
            vm = upbitViewModel
            rvTickers.adapter =
                object : BaseRecyclerViewAdapter<FormatTickers, RvItemListBinding>(
                    layoutRes = R.layout.rv_item_list,
                    bindingId = BR.item
                ) {}
        }
    }

    companion object {
        const val MARKET_NAME = "market name"
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString(MARKET_NAME, marketName)
            Log.d("market3", "" + marketName)
            fragment.arguments = bundle
            return fragment
        }
    }
}
