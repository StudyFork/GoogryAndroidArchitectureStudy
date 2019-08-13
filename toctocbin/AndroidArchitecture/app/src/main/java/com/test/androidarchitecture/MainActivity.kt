package com.test.androidarchitecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.test.androidarchitecture.adpter.ViewpagerAdapter
import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.network.RetrofitClient
import com.test.androidarchitecture.network.RetrofitService
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.adapter.rxjava2.Result.response

class MainActivity : AppCompatActivity() {


    private var viewpagerAdapter: ViewpagerAdapter? = null
    private val titleList = arrayListOf("KRW", "BTC", "ETH", "USDT")
    private val fragmentList: ArrayList<Fragment> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tabLayout.setupWithViewPager(main_viewPager)
        loadMarketData()
    }

    private fun loadMarketData() {
        val retrofitService = RetrofitClient().getClient().create(RetrofitService::class.java)
        retrofitService.loadMarketData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(this::marketResponse, this::marketError)
    }

    private fun marketResponse(marketList: ArrayList<Market>) {
        val krwList: ArrayList<String> = ArrayList()
        val btcList: ArrayList<String> = ArrayList()
        val ethList: ArrayList<String> = ArrayList()
        val usdtList: ArrayList<String> = ArrayList()
        fragmentList.run {
            for (market in marketList) {
                if (market.market.contains("KRW"))
                    krwList.add(market.english_name)
                if (market.market.contains("BTC"))
                    btcList.add(market.english_name)
                if (market.market.contains("ETH"))
                    ethList.add(market.english_name)
                if (market.market.contains("USDT"))
                    usdtList.add(market.english_name)
            }
            add(CoinFragment.getInstance(krwList))
            add(CoinFragment.getInstance(btcList))
            add(CoinFragment.getInstance(ethList))
            add(CoinFragment.getInstance(usdtList))
        }
        viewpagerAdapter = ViewpagerAdapter(supportFragmentManager, fragmentList, titleList)
        main_viewPager.adapter = viewpagerAdapter
    }

    private fun marketError(error: Throwable) {
        Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
    }

}
