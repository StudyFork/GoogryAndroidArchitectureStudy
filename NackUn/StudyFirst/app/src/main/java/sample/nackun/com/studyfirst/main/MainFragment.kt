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
import sample.nackun.com.studyfirst.ui.TickerAdapter
import sample.nackun.com.studyfirst.data.Repository
import sample.nackun.com.studyfirst.data.remote.RemoteDataSource
import sample.nackun.com.studyfirst.network.UpbitApi
import sample.nackun.com.studyfirst.vo.Ticker

class MainFragment : BaseFragment(
    R.layout.main_fragment
), MainContract.View {

    override lateinit var presenter: MainContract.Presenter
    private val tickerAdapter = TickerAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initPresenter()
        initOnClick()
    }

    override fun showTickers(tickers: ArrayList<Ticker>) =
        tickerAdapter.setItems(tickers)

    override fun showMsg(msg: String) =
        showToast(msg)

    private fun initPresenter(){
        MainPresenter(this,
            Repository(RemoteDataSource(
            Retrofit.Builder()
                .baseUrl("https://api.upbit.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UpbitApi::class.java)))
        )
    }

    private fun initOnClick(){
        val onClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                context?.let {
                    tv_market_krw.setTextColor(ContextCompat.getColor(it, R.color.grey))
                    tv_market_btc.setTextColor(ContextCompat.getColor(it, R.color.grey))
                    tv_market_eth.setTextColor(ContextCompat.getColor(it, R.color.grey))
                    tv_market_usdt.setTextColor(ContextCompat.getColor(it, R.color.grey))
                    val selectedMarket = v as TextView
                    selectedMarket.setTextColor(ContextCompat.getColor(it, R.color.indigo))
                    presenter.requestTickers(selectedMarket.text.toString())
                }
            }
        }
        tv_market_krw.setOnClickListener(onClickListener)
        tv_market_btc.setOnClickListener(onClickListener)
        tv_market_eth.setOnClickListener(onClickListener)
        tv_market_usdt.setOnClickListener(onClickListener)

        tickerRecyclerView.adapter = tickerAdapter

        tv_market_krw.callOnClick()
    }
}