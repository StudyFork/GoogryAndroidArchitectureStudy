package com.architecturestudy.upbitmarket

import android.os.Bundle
import android.view.View
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
        binding.vm = UpbitViewModel(
            UpbitRepository(
                UpbitRetrofitDataSource(UpbitRetrofit.retrofit)
            )
        )
        initRecyclerView()
    }

    override fun onDestroy() {
        super.onDestroy()
        CompositeDisposable().dispose()
    }

    private fun initRecyclerView() {
        rv_coin_price.setHasFixedSize(true)
        rv_coin_price.adapter = UpbitAdapter()
    }

    // TODO 뷰 클릭 시 텍스트 색상 변경 코드 참고하기
//    fun setViewColor(view: View) {
//        listOf<View>(
//            tv_market_krw,
//            tv_market_btc,
//            tv_market_eth,
//            tv_market_usdt
//        ).filter { view != it }
//            .map { (it as TextView).setTextColor(Color.GRAY) }
//
//        if (view is TextView) {
//            view.setTextColor(Color.BLUE)
//        }
//    }
}
