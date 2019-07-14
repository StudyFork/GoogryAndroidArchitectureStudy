package com.architecturestudy.upbitmarket

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.architecturestudy.BR
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.base.BaseRecyclerView
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource
import com.architecturestudy.databinding.FragmentUpbitBinding
import com.architecturestudy.databinding.RvUpbitItemBinding
import io.reactivex.disposables.CompositeDisposable

class UpbitFragment : BaseFragment<FragmentUpbitBinding>(
    R.layout.fragment_upbit
) {
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setBinding()
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable().dispose()
    }

    private fun setBinding() {
        val vm = UpbitViewModel(UpbitRepository(UpbitRetrofitDataSource(UpbitRetrofit.retrofit)))
        binding.vm = vm
        binding.lifecycleOwner = this
        vm.errMsg.observe(
            this@UpbitFragment,
            Observer {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
        )
    }

    private fun initRecyclerView() {
        binding.rvCoinPrice.setHasFixedSize(true)
        binding.rvCoinPrice.adapter =
            object : BaseRecyclerView.BaseAdapter<List<Map<String, String>>, RvUpbitItemBinding>(
                R.layout.rv_upbit_item,
                BR.marketList
            ) {}
    }
}
