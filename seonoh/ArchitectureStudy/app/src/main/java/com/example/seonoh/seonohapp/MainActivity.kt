package com.example.seonoh.seonohapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.seonoh.seonohapp.contract.CoinMainContract
import com.example.seonoh.seonohapp.model.Market
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), CoinMainContract.View {

    private lateinit var pagerAdapter: TabPagerAdapter
    private lateinit var toast: Toast
    private lateinit var coinMarketNameList: List<String>
    override val presenter: CoinMainContract.Presenter by lazy { CoinMainPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initView()
        presenter.loadMarketData()
    }

    private fun initView() {
        pagerAdapter = TabPagerAdapter(supportFragmentManager)
        coinViewPager.apply {
            adapter = pagerAdapter
            addOnPageChangeListener(object : TabLayout.TabLayoutOnPageChangeListener(tabLayout) {})
        }

        tabLayout.apply {
            addTab(tabLayout.newTab().setText("KRW"))
            addTab(tabLayout.newTab().setText("BTC"))
            addTab(tabLayout.newTab().setText("ETH"))
            addTab(tabLayout.newTab().setText("USDT"))

            addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(coinViewPager))
        }
    }

    override fun setPager(marketData: List<Market>) {
        pagerAdapter.setData(classifyMarketData(marketData))
    }

    override fun showToast() {
        toast = Toast.makeText(this, resources.getString(R.string.back_text), Toast.LENGTH_SHORT)
        if (toast.view.isShown) {
            finish()
        } else {
            toast.show()
        }
    }

    override fun onBackPressed() {
        showToast()
    }

    private fun classifyMarketData(marketData: List<Market>): ArrayList<String> {

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
