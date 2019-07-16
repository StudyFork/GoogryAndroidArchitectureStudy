package com.architecturestudy.upbitmarket

import android.os.Bundle
import androidx.lifecycle.Observer
import com.architecturestudy.BR
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.base.BaseRecyclerView
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource
import com.architecturestudy.databinding.FragmentUpbitContentsBinding
import com.architecturestudy.databinding.RvUpbitItemBinding

class UpbitContentsFragment(
    private val position: Int
) : BaseFragment<FragmentUpbitContentsBinding>(
    R.layout.fragment_upbit_contents
) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        showContents()
    }

    private fun initBinding() {
        binding.vm =
            UpbitViewModel(UpbitRepository(UpbitRetrofitDataSource(UpbitRetrofit.retrofit)))
    }

    private fun initRecyclerView() {
        binding.rvCoinPrice.run {
            setHasFixedSize(true)
            adapter =
                object :
                    BaseRecyclerView.BaseAdapter<List<Map<String, String>>, RvUpbitItemBinding>(
                        R.layout.rv_upbit_item,
                        BR.marketList
                    ) {}
        }
    }

    private fun showContents() {
        binding.vm?.let {
            it.showMarketPrice(MarketTypes.values()[position].name)
            it.errMsg.observe(
                this,
                Observer {
                    showToast(it.message)
                }
            )
        }
    }

}
