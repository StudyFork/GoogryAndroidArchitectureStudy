package sample.nackun.com.studyfirst.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.main_fragment.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import sample.nackun.com.studyfirst.Base.BaseFragment
import sample.nackun.com.studyfirst.R
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.ui.TickerAdapter
import sample.nackun.com.studyfirst.vo.Ticker

class MainFragment : BaseFragment(
    R.layout.main_fragment
), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    private val tickerAdapter = TickerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPresenter()
        setOnClick()
    }

    override fun showTickers(tickers: List<Map<String, String>>) =
        tickerAdapter.setItems(tickers)

    override fun showMsg(msg: String?) =
        showToast(msg)

    private fun initPresenter() {
        MainPresenter(
            this,
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
    }

    private fun setOnClick() {
        tv_market_krw.setOnClickListener(this::setMarketColor)
        tv_market_btc.setOnClickListener(this::setMarketColor)
        tv_market_eth.setOnClickListener(this::setMarketColor)
        tv_market_usdt.setOnClickListener(this::setMarketColor)

        tickerRecyclerView.adapter = tickerAdapter

        tv_market_krw.callOnClick()
    }

    private fun setMarketColor(view: View){
        listOf<View>(
            tv_market_krw,
            tv_market_btc,
            tv_market_eth,
            tv_market_usdt
        ).filter { view != it }
            .map { (it as TextView).setTextColor(ContextCompat.getColor(it.context, R.color.grey)) }

        if(view is TextView){
            view.setTextColor(ContextCompat.getColor(view.context, R.color.indigo))
            presenter.requestTickers(view.text.toString())
        }
    }
}