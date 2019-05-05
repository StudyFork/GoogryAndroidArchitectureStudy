package com.example.myarchitecutrestudy.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.myarchitecutrestudy.fragment.CoinFragment
import com.example.myarchitecutrestudy.model.MarketAll

class CoinPagerAdapter(fm: FragmentManager?, private val tabCount:Int, marketList:List<MarketAll>) : FragmentPagerAdapter(fm) {

    private var krwList:ArrayList<String> = ArrayList()
    private var btcList:ArrayList<String> = ArrayList()
    private var ethList:ArrayList<String> = ArrayList()
    private var usdtList:ArrayList<String> = ArrayList()

    init {
        marketList.forEach {
            when(it.market.substring(0,3)){
                "KRW" -> krwList.add(it.market)
                "BTC" -> btcList.add(it.market)
                "ETH" -> ethList.add(it.market)
                "USD" -> usdtList.add(it.market)
            }
        }
    }

    override fun getItem(position: Int): Fragment? {

        return when (position) {
            0 -> {
                CoinFragment.newInstance(krwList)
            }
            1 ->{
                CoinFragment.newInstance(btcList)
            }
            2 -> {
                CoinFragment.newInstance(ethList)
            }
            3->{
                CoinFragment.newInstance(usdtList)
            }
            else -> null
        }

    }

    override fun getCount(): Int {
        return tabCount
    }

}