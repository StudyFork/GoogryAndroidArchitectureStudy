package com.test.androidarchitecture

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.test.androidarchitecture.adpter.ViewpagerAdapter
import com.test.androidarchitecture.data.Market
import com.test.androidarchitecture.data.MarketTitle
import com.test.androidarchitecture.network.RetrofitClient
import com.test.androidarchitecture.network.RetrofitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    private var viewpagerAdapter: ViewpagerAdapter? = null
    private val retrofitClient by lazy { RetrofitClient.getInstance().getClient() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        main_tabLayout.setupWithViewPager(main_viewPager)
        loadMarketData()
    }

    @SuppressLint("CheckResult")
    private fun loadMarketData() {
        val retrofitService = retrofitClient.create(RetrofitService::class.java)
        retrofitService.loadMarketData().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ t: List<Market>? ->
                run {
                    val marketTitle = t?.groupBy { it.market.split("-")[0] }
                        ?.map { map ->
                            val title = map.key
                            val markets = map.value.joinToString(separator = ",") {
                                it.market
                            }
                            MarketTitle(title, markets)
                        }
                    viewpagerAdapter =
                        marketTitle?.let {
                            ViewpagerAdapter(supportFragmentManager, it)
                        }
                    main_viewPager.offscreenPageLimit = 3
                    main_viewPager.adapter = viewpagerAdapter
                }
            }, { t: Throwable? -> Toast.makeText(this, t?.message, Toast.LENGTH_SHORT).show() })
    }

}