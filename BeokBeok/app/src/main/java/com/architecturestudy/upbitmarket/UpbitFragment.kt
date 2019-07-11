package com.architecturestudy.upbitmarket

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource
import com.architecturestudy.databinding.FragmentUpbitBinding
import com.architecturestudy.upbitmarket.adapter.UpbitAdapter
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
        val vm = UpbitViewModel(
            UpbitRepository(
                UpbitRetrofitDataSource(
                    UpbitRetrofit.retrofit
                )
            )
        )
        binding.vm = vm
        registerPropertyChangedCallbacks(vm)
    }

    private fun registerPropertyChangedCallbacks(vm: UpbitViewModel) {
        vm.errMsg.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (sender is ObservableField<*>) {
                    Toast.makeText(
                        context,
                        (sender.get() as Throwable).message,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })
    }

    private fun initRecyclerView() {
        binding.rvCoinPrice.setHasFixedSize(true)
        binding.rvCoinPrice.adapter = UpbitAdapter()
    }
}
