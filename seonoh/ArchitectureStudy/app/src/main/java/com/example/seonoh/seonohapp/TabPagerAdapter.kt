package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.seonoh.seonohapp.MainActivity.Companion.btcMarketData
import com.example.seonoh.seonohapp.MainActivity.Companion.ethMarketData
import com.example.seonoh.seonohapp.MainActivity.Companion.krwMarketData
import com.example.seonoh.seonohapp.MainActivity.Companion.usdtMarketData

class TabPagerAdapter : FragmentStatePagerAdapter {

    private var tabCount = 0

    constructor(fm: FragmentManager, tabcount: Int) : super(fm) {
        this.tabCount = tabcount
    }


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> return CoinFragment.newInstance(KRW_TYPE, krwMarketData)

            1 -> return CoinFragment.newInstance(BTC_TYPE, btcMarketData)

            2 -> return CoinFragment.newInstance(ETH_TYPE, ethMarketData)

            3 -> return CoinFragment.newInstance(USDT_TYPE, usdtMarketData)

            else -> return throw NullPointerException()
        }
    }

    override fun getCount(): Int = tabCount
}