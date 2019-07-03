package com.architecturestudy.upbitmarket

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.data.upbit.UpbitRepository
import com.architecturestudy.data.upbit.service.UpbitRetrofit
import com.architecturestudy.data.upbit.source.UpbitRetrofitDataSource
import com.architecturestudy.databinding.FragmentUpbitBinding
import com.architecturestudy.upbitmarket.recyclerview.UpbitAdapter
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_upbit.*

class UpbitFragment : BaseFragment<FragmentUpbitBinding>(
    R.layout.fragment_upbit
) {
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
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
        vm.selectedTextView.addOnPropertyChangedCallback(
            object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    if (sender is ObservableField<*>) {
                        setViewColor((sender.get() as TextView))
                    }
                }
            }
        )
    }

    private fun initRecyclerView() {
        rv_coin_price.setHasFixedSize(true)
        rv_coin_price.adapter = UpbitAdapter()
    }

    private fun setViewColor(view: View) {
        listOf<View>(
            tv_market_krw,
            tv_market_btc,
            tv_market_eth,
            tv_market_usdt
        ).filter { view != it }
            .map { (it as TextView).setTextColor(Color.GRAY) }

        if (view is TextView) {
            view.setTextColor(Color.BLUE)
        }
    }
}
