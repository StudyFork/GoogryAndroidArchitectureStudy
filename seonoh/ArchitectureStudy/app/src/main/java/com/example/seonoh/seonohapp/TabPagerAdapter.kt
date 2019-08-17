package com.example.seonoh.seonohapp

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.seonoh.seonohapp.MainActivity.Companion.btcMarketData
import com.example.seonoh.seonohapp.MainActivity.Companion.ethMarketData
import com.example.seonoh.seonohapp.MainActivity.Companion.krwMarketData
import com.example.seonoh.seonohapp.MainActivity.Companion.usdtMarketData

class TabPagerAdapter(fm: FragmentManager, tabcount: Int) : FragmentStatePagerAdapter(fm) {

    private val tabCount = tabcount



    override fun getItem(position: Int): Fragment {
return when (position) {
            0 -> CoinFragment.newInstance(KRW_TYPE, krwMarketData)

            1 -> CoinFragment.newInstance(BTC_TYPE, btcMarketData)

            2 -> CoinFragment.newInstance(ETH_TYPE, ethMarketData)

            3 -> CoinFragment.newInstance(USDT_TYPE, usdtMarketData)

            else -> throw Throwable()
        }
            0 -> return CoinFragment.newInstance(KRW_TYPE, krwMarketData)

            1 -> return CoinFragment.newInstance(BTC_TYPE, btcMarketData)

            2 -> return CoinFragment.newInstance(ETH_TYPE, ethMarketData)

            3 -> return CoinFragment.newInstance(USDT_TYPE, usdtMarketData)

            else -> return throw Throwable()
        }
    }

    override fun getCount(): Int = tabCount
}
