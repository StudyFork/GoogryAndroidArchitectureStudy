package com.test.androidarchitecture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.androidarchitecture.adpter.ViewpagerAdapter
import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.network.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var viewpagerAdapter: ViewpagerAdapter? = null
    private val retrofitService by lazy { RetrofitClient.getInstance().retrofitService }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tabLayout.setupWithViewPager(main_viewPager)
        viewpagerAdapter = ViewpagerAdapter(supportFragmentManager)
        main_viewPager.offscreenPageLimit = 3
        main_viewPager.adapter = viewpagerAdapter
        loadMarketData()
    }

    @SuppressLint("CheckResult")
    private fun loadMarketData() {
        retrofitService.loadMarketData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: List<Market> ->
                val marketTitle = t.groupBy { it.market.substringBefore("-") }
                    .map { map ->
                        val title = map.key
                        val markets = map.value.joinToString(separator = ",") {
                            it.market
                        }
                        MarketTitle(title, markets)
                    }
                viewpagerAdapter?.setData(marketTitle)
            }, { t: Throwable -> Toast.makeText(this, t.message, Toast.LENGTH_SHORT).show() })
    }

}