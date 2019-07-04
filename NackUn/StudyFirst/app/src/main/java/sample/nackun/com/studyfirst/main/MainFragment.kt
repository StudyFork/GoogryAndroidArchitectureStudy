package sample.nackun.com.studyfirst.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.BR
import sample.nackun.com.studyfirst.Base.BaseFragment
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.databinding.MainFragmentBinding
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.ui.TickerAdapter

class MainFragment : BaseFragment<MainFragmentBinding>(
    R.layout.main_fragment
) {

    private val tickerAdapter = TickerAdapter()

    private lateinit var vm: MainViewModel

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        initViewModel()
        setOnClick()
    }

    private fun initViewModel() {
        vm = MainViewModel(
            Repository(
                RemoteDataSource(
                    Retrofit.Builder()
                        .baseUrl("https://api.upbit.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(UpbitApi::class.java)
                )
            )
        )
        binding.setVariable(BR.vm, vm)
    }

    private fun setOnClick() {
        tv_market_krw.setOnClickListener(this::setMarketColor)
        tv_market_btc.setOnClickListener(this::setMarketColor)
        tv_market_eth.setOnClickListener(this::setMarketColor)
        tv_market_usdt.setOnClickListener(this::setMarketColor)

        tickerRecyclerView.adapter = tickerAdapter

        tv_market_krw.callOnClick()
    }

    private fun setMarketColor(view: View) {
        listOf<View>(
            tv_market_krw,
            tv_market_btc,
            tv_market_eth,
            tv_market_usdt
        ).filter { view != it }
            .map { (it as TextView).setTextColor(ContextCompat.getColor(it.context, R.color.grey)) }

        if (view is TextView) {
            view.setTextColor(ContextCompat.getColor(view.context, R.color.indigo))
            vm.requestTickers(view)
        }
    }
}