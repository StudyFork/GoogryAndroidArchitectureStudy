package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.Retrofit.retrofit
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentMainBinding


class MainFragment : BaseFragment() {


    private val upbitApi by lazy { retrofit.create(UpbitApi::class.java) }

    private lateinit var marketName: String

    private val error = "error"

    private lateinit var binding: FragmentMainBinding

    private lateinit var mainVm: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.fragmentTickerListRv.adapter = TickerListAdapter()
        arguments?.let {
            it.getString("marketName")?.let { marketName ->
                if (marketName == error) {
                    showErrorToast()
                    return
                }
                this.marketName = marketName
            }
        }
        mainVm = MainViewModel(
            upbitRepository = UpbitRepository(
                UpbitRemoteDataSource(upbitApi)
            )
        )
        mainVm.loadMarketList(marketName)
        binding.run {
            mainViewModel = mainVm
        }
    }

    private fun showErrorToast() {
        toastM(getString(com.aiden.aiden.architecturepatternstudy.R.string.all_error_load_data_fail))
    }

}