package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.databinding.Observable
import com.aiden.aiden.architecturepatternstudy.BR
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.Retrofit.retrofit
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.base.SimpleRecyclerView
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentMainBinding
import com.aiden.aiden.architecturepatternstudy.databinding.ItemTickerBinding


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {


    private val upbitApi by lazy { retrofit.create(UpbitApi::class.java) }

    private lateinit var marketName: String

    private val error = "error"

    private lateinit var mainVm: MainViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        binding {
            fragmentTickerListRv.adapter =
                object : SimpleRecyclerView.Adapter<TickerResponse, ItemTickerBinding>(
                    layoutRes = R.layout.item_ticker,
                    bindingVariableId = BR.item
                ) {}
            mainViewModel = mainVm
            mainVm.isDataLoadingError.addOnPropertyChangedCallback(object :
                Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    mainVm.isDataLoadingError.get()?.let {
                        if (!it) showErrorToast()
                    }
                }

            })
        }
    }

    private fun showErrorToast() {
        toastM(getString(R.string.all_error_load_data_fail))
    }

}