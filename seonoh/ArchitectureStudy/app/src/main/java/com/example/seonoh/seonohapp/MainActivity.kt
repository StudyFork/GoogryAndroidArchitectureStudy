package com.example.seonoh.seonohapp

import android.os.Bundle
import android.util.Log
import com.example.seonoh.seonohapp.databinding.ActivityMainBinding
import com.example.seonoh.seonohapp.model.MainViewModel
import com.example.seonoh.seonohapp.model.Market
import com.example.seonoh.seonohapp.repository.CoinRepositoryImpl
import com.google.android.material.tabs.TabLayout
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainActivity : BaseActivity<ActivityMainBinding>(
    R.layout.activity_main
) {

    private lateinit var coinMarketNameList: List<String>
    private var pagerAdapter = TabPagerAdapter(supportFragmentManager)
    private val coinRepository = CoinRepositoryImpl()
    private val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        loadData()
    }

    private fun loadData() {
        compositeDisposable.add(
            coinRepository.sendMarket()
                .map {
                    viewModel.marketInfo.set(refineData(it))
                }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, {
                    Log.e("currentPriceInfo", "Main Network failed!! ${it.message}")
                })
        )
    }

    private fun initView() {
        binding.run {
            mainViewModel = viewModel
            coinViewPager.run {
                adapter = pagerAdapter
                addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
            }
            tabLayout.run {
                addTab(tabLayout.newTab().setText("KRW"))
                addTab(tabLayout.newTab().setText("BTC"))
                addTab(tabLayout.newTab().setText("ETH"))
                addTab(tabLayout.newTab().setText("USDT"))
                addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))
            }
        }
    }

    private fun refineData(marketData: List<Market>): List<String> {

        coinMarketNameList = marketData.map {
            it.market.substringBefore("-")
        }.distinct()

        val marketDataList = ArrayList<String>()

        coinMarketNameList.forEach { title ->

            marketDataList += (marketData.filter {
                it.market.substringBefore("-") == title
            }.joinToString(",") {
                it.market
            })
        }

        return marketDataList
    }

}
