package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.aiden.aiden.architecturepatternstudy.BR
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.UpbitApi
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.base.SimpleRecyclerView
import com.aiden.aiden.architecturepatternstudy.data.source.UpbitRepository
import com.aiden.aiden.architecturepatternstudy.data.source.local.UpbitLocalDataSource
import com.aiden.aiden.architecturepatternstudy.data.source.remote.UpbitRemoteDataSource
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentTickerMainBinding
import com.aiden.aiden.architecturepatternstudy.databinding.ItemTickerBinding
import com.aiden.aiden.architecturepatternstudy.domain.UpbitDatabase
import org.koin.android.ext.android.inject


class MainTickerFragment : BaseFragment<FragmentTickerMainBinding>(R.layout.fragment_ticker_main) {

    private val upbitApi: UpbitApi by inject()

    private lateinit var marketName: String

    private val error = "error"

    private lateinit var mainVm: MainViewModel

    private lateinit var mainSearchVm: MainSearchViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        // 각 fragment의 마켓이름을 가져옴
        arguments?.let {
            it.getString("marketName")?.let { marketName ->
                if (marketName == error) {
                    showErrorToast()
                    return
                }
                this.marketName = marketName
            }
        }

        // ticker 리스트를 뿌리기 위한 ViewModel 생성
        mainVm = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(UpbitRepository::class.java).newInstance(
                    UpbitRepository(
                        UpbitRemoteDataSource(upbitApi),
                        UpbitLocalDataSource(UpbitDatabase(context!!))
                    )
                )
            }
        })[MainViewModel::class.java]

        // 검색어를 공유하기 위한 ViewModel 생성
        mainSearchVm =
            ViewModelProviders.of(activity!!)[MainSearchViewModel::class.java]

        // 검색어가 있으면 검색결과가 나오고, 없으면 해당 마켓의 코인리스트가 나옴
        val searchKeyword = mainSearchVm.searchKeyword.value
        if (searchKeyword.isNullOrBlank()) {
            mainVm.loadMarketList(marketName)
        } else {
            mainVm.searchTickerByKeyword(listOf("$marketName-$searchKeyword"))
        }

        binding {
            fragmentTickerListRv.adapter =
                object : SimpleRecyclerView.Adapter<TickerResponse, ItemTickerBinding>(
                    layoutRes = R.layout.item_ticker,
                    bindingVariableId = BR.item
                ) {}
            mainViewModel = mainVm

        }

        // 데이터를 불러오지 못할 경우
        val isDataLoadingErrorObserver = Observer<Boolean> {
            if (it) showErrorToast()
        }
        mainVm.isDataLoadingError.observe(this, isDataLoadingErrorObserver)

        // 검색어에 따라서 검색결과를 보여줌. 검색어가 빈칸이 되면 해당 마켓의 코인리스트가 나옴
        val searchKeywordObserver = Observer<String> {
            if (it.isNullOrBlank()) {
                mainVm.loadMarketList(marketName)
                return@Observer
            }
            mainVm.searchTickerByKeyword(listOf("$marketName-$it"))
        }
        mainSearchVm.searchKeyword.observe(this, searchKeywordObserver)

    }

    private fun showErrorToast() {

        toastM(getString(R.string.all_error_load_data_fail))

    }
}

