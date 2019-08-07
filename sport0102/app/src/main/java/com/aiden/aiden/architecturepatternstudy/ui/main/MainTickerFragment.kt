package com.aiden.aiden.architecturepatternstudy.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import com.aiden.aiden.architecturepatternstudy.BR
import com.aiden.aiden.architecturepatternstudy.R
import com.aiden.aiden.architecturepatternstudy.api.model.TickerResponse
import com.aiden.aiden.architecturepatternstudy.base.BaseFragment
import com.aiden.aiden.architecturepatternstudy.base.SimpleRecyclerView
import com.aiden.aiden.architecturepatternstudy.databinding.FragmentTickerMainBinding
import com.aiden.aiden.architecturepatternstudy.databinding.ItemTickerBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainTickerFragment :
    BaseFragment<FragmentTickerMainBinding, MainViewModel>(R.layout.fragment_ticker_main) {

    private lateinit var marketName: String

    private val error = "error"

    private val mainSearchVm by sharedViewModel<MainSearchViewModel>()

    override val viewModel by viewModel<MainViewModel>()

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        // 각 fragment 의 마켓이름을 가져옴
        arguments?.let {
            it.getString("marketName")?.let { marketName ->
                if (marketName == error) {
                    showErrorToast()
                    return
                }
                this.marketName = marketName
            }
        }

        // 검색어가 있으면 검색결과가 나오고, 없으면 해당 마켓의 코인리스트가 나옴
        val searchKeyword = mainSearchVm.searchKeyword.value
        if (searchKeyword.isNullOrBlank()) {
            viewModel.loadMarketList(marketName)
        } else {
            viewModel.searchTickerByKeyword(listOf("$marketName-$searchKeyword"))
        }

        binding {
            fragmentTickerListRv.adapter =
                object : SimpleRecyclerView.Adapter<TickerResponse, ItemTickerBinding>(
                    layoutRes = R.layout.item_ticker,
                    bindingVariableId = BR.item
                ) {}
        }

        // 데이터를 불러오지 못할 경우
        val isDataLoadingErrorObserver = Observer<Boolean> {
            if (it) showErrorToast()
        }
        viewModel.isDataLoadingError.observe(this, isDataLoadingErrorObserver)

        // 검색어에 따라서 검색결과를 보여줌. 검색어가 빈칸이 되면 해당 마켓의 코인리스트가 나옴
        val searchKeywordObserver = Observer<String> {
            if (it.isNullOrBlank()) {
                viewModel.loadMarketList(marketName)
                return@Observer
            }
            viewModel.searchTickerByKeyword(listOf("$marketName-$it"))
        }
        mainSearchVm.searchKeyword.observe(this, searchKeywordObserver)

    }

    private fun showErrorToast() {

        toastM(getString(R.string.all_error_load_data_fail))

    }
}

