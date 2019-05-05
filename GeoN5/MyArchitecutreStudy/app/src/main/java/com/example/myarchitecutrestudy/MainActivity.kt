package com.example.myarchitecutrestudy

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.example.myarchitecutrestudy.adapter.CoinPagerAdapter
import com.example.myarchitecutrestudy.model.MarketAll
import com.example.myarchitecutrestudy.network.CoinService
import com.example.myarchitecutrestudy.utils.RetrofitUtil
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val TAG : String = MainActivity::class.java.simpleName
    private lateinit var adapter: CoinPagerAdapter
    private var marketList:List<MarketAll> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "업비트(Upbit)"

        setTabLayout()
        getMarketInfo()
    }

    private fun setTabLayout(){
        tab.addTab(tab.newTab().setText("KRW"))
        tab.addTab(tab.newTab().setText("BTC"))
        tab.addTab(tab.newTab().setText("ETH"))
        tab.addTab(tab.newTab().setText("USDT"))
    }

    private fun getMarketInfo(){
        val coinService: CoinService = RetrofitUtil.retrofit.create(CoinService::class.java)
        val call: Call<List<MarketAll>> = coinService.getMarket()

        call.enqueue(object: Callback<List<MarketAll>>{
            override fun onFailure(call: Call<List<MarketAll>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<MarketAll>>, response: Response<List<MarketAll>>) {
                Log.d("MainActivity",response.body().toString())
                response.body()?.let {
                    Toast.makeText(applicationContext, "ActonResponse!", Toast.LENGTH_SHORT).show()
                    Log.d(TAG, it.toString())
                    marketList = it
                    setViewpager()
                    //adapter.setList(it)
                } ?: Toast.makeText(applicationContext,"액티비티 문제가 발생했습니다!", Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setViewpager(){
        adapter = CoinPagerAdapter(supportFragmentManager, tab.tabCount,marketList)
        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tab))

        tab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

        })

    }

}