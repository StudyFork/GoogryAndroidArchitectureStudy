package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.aiden.aiden.architecturepatternstudy.BR
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.base.SimpleRecyclerView
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitLocalDataSource
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentMainBinding
import com.aiden.aiden.architecturepatternstudy.databinding.ItemTickerBinding
import com.aiden.aiden.architecturepatternstudy.domain.UpbitDatabase
import com.aiden.aiden.architecturepatternstudy.ext.replaceAll
import org.koin.android.ext.android.inject


class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val upbitApi: UpbitApi by inject()

    private lateinit var marketName: String

    private val error = "error"

    private lateinit var mainVm: MainViewModel

    private val db by lazy {
        context?.let {
            UpbitDatabase(it)
        }
    }

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
                UpbitRemoteDataSource(upbitApi),
                UpbitLocalDataSource(db)
            )
        )
        mainVm.loadMarketList(marketName)
        binding {
            fragmentTickerListRv.adapter =
                object : SimpleRecyclerView.Adapter<TickerResponse, ItemTickerBinding>(
                    layoutRes = R.layout.item_ticker,
                    bindingVariableId = BR.item
                ) {}
            val tickerListObserver = Observer<List<TickerResponse>> {
                fragmentTickerListRv.replaceAll(it)
            }
            mainVm.tickerList.observe(this@MainFragment, tickerListObserver)

        }
        val isDataLoadingErrorObserver = Observer<Boolean> {
            if (it) showErrorToast()
        }
        mainVm.isDataLoadingError.observe(this@MainFragment, isDataLoadingErrorObserver)

    }

    private fun showErrorToast() {

        toastM(getString(R.string.all_error_load_data_fail))

    }
}

