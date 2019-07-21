package com.architecturestudy.upbitmarket

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.architecturestudy.BR
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.base.BaseRecyclerView
import com.architecturestudy.data.common.MarketTypes
import com.architecturestudy.data.upbit.source.UpbitRepository
import com.architecturestudy.data.upbit.source.local.UpbitLocalDataSource
import com.architecturestudy.data.upbit.source.local.UpbitRoom
import com.architecturestudy.data.upbit.source.remote.UpbitRemoteDataSource
import com.architecturestudy.data.upbit.source.remote.UpbitRetrofit
import com.architecturestudy.databinding.FragmentUpbitContentsBinding
import com.architecturestudy.databinding.RvUpbitItemBinding

class UpbitContentsFragment : BaseFragment<FragmentUpbitContentsBinding>(
    R.layout.fragment_upbit_contents
) {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initBinding()
        initRecyclerView()
        showContents()
    }

    private fun initBinding() {
        binding.vm = ViewModelProviders.of(
            this,
            object : ViewModelProvider.Factory {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                    UpbitViewModel(
                        UpbitRepository(
                            UpbitLocalDataSource(UpbitRoom.getDao(context)),
                            UpbitRemoteDataSource(UpbitRetrofit.service)
                        )
                    ) as T
            }
        )[UpbitViewModel::class.java]
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
        val position = arguments?.getInt("marketType", 0) ?: 0
        binding.vm?.let {
            it.showMarketPrice(MarketTypes.values()[position].name)
            it.errMsg.observe(
                this,
                Observer { throwable ->
                    showToast(throwable.message)
                }
            )
        }
    }

}
