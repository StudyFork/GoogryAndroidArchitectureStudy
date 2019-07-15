package com.architecturestudy.upbitmarket

import android.os.Bundle
import androidx.lifecycle.Observer
import com.architecturestudy.BR
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.base.BaseRecyclerView
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource
import com.architecturestudy.databinding.FragmentUpbitContentsBinding
import com.architecturestudy.databinding.RvUpbitItemBinding

class UpbitContentsFragment : BaseFragment<FragmentUpbitContentsBinding>(
    R.layout.fragment_upbit_contents
) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setBinding()
        initRecyclerView()
    }

    private fun setBinding() {
        val vm = UpbitViewModel(UpbitRepository(UpbitRetrofitDataSource(UpbitRetrofit.retrofit)))
        binding.vm = vm
        arguments?.let {
            it.getString("marketType")?.let { data ->
                vm.showMarketPrice(data)
            }
        }
        vm.errMsg.observe(
            this,
            Observer {
                showToast(it.message)
            }
        )
    }

    private fun initRecyclerView() {
        binding.run {
            rvCoinPrice.setHasFixedSize(true)
            rvCoinPrice.adapter =
                object :
                    BaseRecyclerView.BaseAdapter<List<Map<String, String>>, RvUpbitItemBinding>(
                        R.layout.rv_upbit_item,
                        BR.marketList
                    ) {}
        }
    }

}
