package com.example.mystudy.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mystudy.R
import com.example.mystudy.adapter.TickerAdapter
import com.example.mystudy.data.FormatTickers
import com.example.mystudy.data.UpbitRepository
import com.example.mystudy.data.remote.UpbitRemoteDataSourceImpl
import com.example.mystudy.databinding.FragmentUpbitBinding
import com.example.mystudy.viewmodel.UpbitViewModel
import kotlinx.coroutines.channels.ticker
import org.jetbrains.anko.support.v4.toast

class UpbitFragment : Fragment(){

    private var tickerAdapter= TickerAdapter()
    private lateinit var binding : FragmentUpbitBinding
    private lateinit var upbitViewModel: UpbitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_upbit,
            container,
            false
        )
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val firstMarket = arguments?.getString(MARKET_NAME)

        initViewModel()
        upbitViewModel.getTicker(firstMarket)
    }

    private fun initViewModel() {
        upbitViewModel= UpbitViewModel(
            UpbitRepository.getInstance(UpbitRemoteDataSourceImpl),
            tickerAdapter
        )
        binding.vm = upbitViewModel
    }


    companion object {
        const val MARKET_NAME = "market name"
        fun newInstance(marketName: String): UpbitFragment {
            val fragment = UpbitFragment()
            val bundle = Bundle()

            bundle.putString(MARKET_NAME, marketName)
            Log.d("market3",""+marketName)
            fragment.arguments = bundle
            return fragment
        }
    }
}
