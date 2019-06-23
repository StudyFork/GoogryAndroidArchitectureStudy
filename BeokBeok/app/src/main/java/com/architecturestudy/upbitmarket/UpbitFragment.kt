package com.architecturestudy.upbitmarket


import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.architecturestudy.R
import com.architecturestudy.base.BaseFragment
import com.architecturestudy.data.UpbitRepository
import com.architecturestudy.data.source.UpbitRetrofitDataSource
import com.architecturestudy.data.source.UpbitService
import com.architecturestudy.upbitmarket.recyclerview.DividerItemDecoration
import com.architecturestudy.upbitmarket.recyclerview.UpbitAdapter
import kotlinx.android.synthetic.main.fragment_upbit.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UpbitFragment : BaseFragment(), UpbitContract.View {

    override val presenter: UpbitContract.Presenter by lazy {
        UpbitPresenter(
            this, UpbitRepository(
                UpbitRetrofitDataSource(
                    Retrofit.Builder()
                        .baseUrl("https://api.upbit.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(
                            OkHttpClient.Builder().addInterceptor(
                                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
                            ).build()
                        )
                        .build()
                        .create(UpbitService::class.java)
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upbit, container, false)
        presenter.start()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        initRecyclerView()
    }

    override fun updateMarketPrice(marketPrice: List<Map<String, String>>) {
        setMainAdapter(marketPrice)
    }

    override fun showErrMsg(t: Throwable) {
        showToast(t.message)
    }

    private fun initRecyclerView() {
        rv_coin_price.addItemDecoration(DividerItemDecoration(activity as UpbitActivity))
        rv_coin_price.setHasFixedSize(true)
    }

    private fun setMainAdapter(marketPrice: List<Map<String, String>>) {
        val adapter = UpbitAdapter()
        adapter.setMarketPrices(marketPrice.sortedBy { it["coinName"] })
        rv_coin_price.adapter = adapter
    }

    private fun initClickListener() {
        tv_market_krw.setOnClickListener { setViewColor(tv_market_krw) }
        tv_market_btc.setOnClickListener { setViewColor(tv_market_btc) }
        tv_market_eth.setOnClickListener { setViewColor(tv_market_eth) }
        tv_market_usdt.setOnClickListener { setViewColor(tv_market_usdt) }
    }

    private fun setViewColor(view: View) {
        listOf<View>(tv_market_krw, tv_market_btc, tv_market_eth, tv_market_usdt)
            .filter { view != it }
            .map { (it as TextView).setTextColor(Color.GRAY) }

        if (view is TextView) {
            view.setTextColor(Color.BLUE)
            presenter.showMarketPrice(view.text.toString())
        }
    }
}
